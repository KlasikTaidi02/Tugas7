package com.example.tugas4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas4.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private lateinit var  dataBase: DatabaseReference
    private lateinit var  detailList: ArrayList<Detail>
    private lateinit var  detailadapter: RecyclerAdapter
    private lateinit var  button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_create)


        var judul: EditText = findViewById(R.id.et_judul)
        var namapenulis: EditText = findViewById(R.id.et_namapenulis)
        var tahun: EditText = findViewById(R.id.et_tahunterbit)
        var kategori: EditText = findViewById(R.id.et_kategori)
        var cover : EditText = findViewById(R.id.url)

        button = findViewById(R.id.createfinalbtn)

        button.setOnClickListener {

            dataBase = FirebaseDatabase.getInstance().getReference("Detail")

            val detail = Detail(
                "2",
                judul.text.toString(),
                namapenulis.text.toString(),
                tahun.text.toString(),
                kategori.text.toString(),
                cover.text.toString()
            )

            dataBase.child(judul.text.toString()).setValue(detail).addOnSuccessListener {

                Toast.makeText(this, "Data Berhasil Dimasukkan", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this, "Data Tidak Berhasil Dimasukkan", Toast.LENGTH_SHORT).show()
            }

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }

    }
}