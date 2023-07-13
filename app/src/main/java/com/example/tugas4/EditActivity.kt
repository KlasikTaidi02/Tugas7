package com.example.tugas4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas4.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private lateinit var  dataBase: DatabaseReference
    private lateinit var  detailList: ArrayList<Detail>
    private lateinit var  detailadapter: RecyclerAdapter
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val temp = intent.getParcelableExtra<Detail>("detail")

        var judul: EditText = findViewById(R.id.et_editjudul)
        var namapenulis: EditText = findViewById(R.id.et_editnamapenulis)
        var tahun: EditText = findViewById(R.id.et_edittahunterbit)
        var kategori: EditText = findViewById(R.id.et_editkategori)
        var cover : EditText = findViewById(R.id.editurl)
        builder = AlertDialog.Builder(this)

        if (temp != null) {
            var id = temp.id
            judul.setText(temp.judul)
            namapenulis.setText(temp.namaPenulis)
            tahun.setText(temp.tahun)
            kategori.setText(temp.kategori)
            cover.setText(temp.url)
        }

        var judulawal = temp?.judul
        var edit: Button = findViewById(R.id.editfinalbtn)

        edit.setOnClickListener {


            var temp2: Detail? = temp?.let { it1 ->
                Detail(
                    it1.id,
                    judul.text.toString(),
                    namapenulis.text.toString(),
                    tahun.text.toString(),
                    kategori.text.toString(),
                    cover.text.toString()
                )
            }

            builder.setTitle("PERHATIAN")
                .setMessage("Ingin Menghapus Data")
                .setCancelable(true)
                .setPositiveButton("Ya") { dialogInterface, it ->
                    if (judulawal != null) {
                        if (temp2 != null) {
                            updateData(judulawal, temp2)
                        }
                    }
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                }
                .setNegativeButton("Tidak") { dialogInterface, it ->

                }

        }

        judul.text.clear()
        namapenulis.text.clear()
        tahun.text.clear()
        kategori.text.clear()
        cover.text.clear()
    }

    private fun updateData(judulawal:String , temp:Detail) {

        dataBase = FirebaseDatabase.getInstance().getReference("Detail")

        val detail = mapOf<String, String>(

            "judul" to temp.judul,
            "namapenulis" to temp.namaPenulis,
            "tahun" to temp.tahun,
            "kategori" to temp.kategori,
            "url" to temp.url

        )

        dataBase.child(judulawal).updateChildren(detail).addOnSuccessListener {

            Toast.makeText(this, "Data Berhasil Diedit", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {

            Toast.makeText(this, "Data Tidak Berhasil Diedit", Toast.LENGTH_SHORT).show()
        }

    }
}
