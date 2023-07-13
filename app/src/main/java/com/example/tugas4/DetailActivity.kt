package com.example.tugas4



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tugas4.R.layout.activity_detail
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var  dataBase: DatabaseReference
    private lateinit var  detailList: ArrayList<Detail>
    private lateinit var  detailadapter: RecyclerAdapter
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_detail)

        val temp = intent.getParcelableExtra<Detail>("detail")

        if(temp!= null){

            val judul: TextView = findViewById(R.id.tv_judul)
            val namapenerbit: TextView = findViewById(R.id.tv_namaPenerbit)
            val tahun: TextView = findViewById(R.id.tv_tahun)
            val kategori : TextView = findViewById(R.id.tv_kategori)
            val cover: ImageView = findViewById(R.id.imageDetail)

            judul.text = "Nama :  ${temp.judul}"
            namapenerbit.text = "Email :  ${temp.namaPenulis}"
            tahun.text = "Jurusan :  ${temp.tahun}"
            kategori.text = "Semester : ${temp.kategori}"

            Picasso.get().load(temp.url).into(cover)

            var edit: Button = findViewById(R.id.editbtn)
            var hapus: Button = findViewById(R.id.hapusbtn)
            builder = AlertDialog.Builder(this)

            edit.setOnClickListener {

                val i = Intent(this,EditActivity::class.java)
                i.putExtra("detail", temp )
                startActivity(i)

            }

            hapus.setOnClickListener {

                    builder.setTitle("PERHATIAN")
                        .setMessage("Ingin Menghapus Data")
                        .setCancelable(true)
                        .setPositiveButton("Ya"){dialogInterface, it->
                            deleteData(temp.judul)
                            val i = Intent(this,MainActivity::class.java)
                            startActivity(i)
                        }
                        .setNegativeButton("Tidak"){dialogInterface, it->
                            dialogInterface.cancel()
                        }


            }



        }

    }

    private fun deleteData(judul:String) {

        dataBase = FirebaseDatabase.getInstance().getReference("Detail")
        dataBase.child(judul).removeValue().addOnSuccessListener {

            Toast.makeText(this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{

            Toast.makeText(this, "Data Tidak Berhasil Dihapus", Toast.LENGTH_SHORT).show()

        }

    }
}