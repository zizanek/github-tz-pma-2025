package com.example.myapp004moreactivities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second)

        val twInfo = findViewById<TextView>(R.id.twInfo)

        //Načtení daz intentu
        val nickname = intent.getStringExtra("NICK_NAME")
        twInfo.text = "Data z první aktivity. Přezdívka: $nickname"

        val btnClose = findViewById<Button>(R.id.btnClose)
        btnClose.setOnClickListener {
            finish()
        }
    }
}