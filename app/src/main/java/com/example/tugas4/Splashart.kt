package com.example.tugas4

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Splashart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashart)

        val logo : ImageView = findViewById<ImageView>(R.id.iv_note4)

        logo.alpha = 0f
        logo.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out)
            finish()
        }


    }
}