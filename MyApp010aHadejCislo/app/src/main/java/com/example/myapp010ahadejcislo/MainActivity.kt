package com.example.myapp010ahadejcislo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp010ahadejcislo.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var secretNumber = 0
    private var attempts = 0 // počítadlo pokusů

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newNumber()

        binding.btnCheck.setOnClickListener {
            val guessTip = binding.etGuess.toString()
            if(guessTip.isEmpty()) {
                Toast.makeText(this, "Musíš zadat číslo!!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val guessTipInt = guessTip.toInt()
            attempts++

            if(guessTipInt == secretNumber) {
                Toast.makeText(this, "Správně :-). Počet pokusů: $attempts", Toast.LENGTH_SHORT).show()
                newNumber()
                attempts = 0
            } else if(guessTipInt < secretNumber) {
                Toast.makeText(this, "Zkus větší číslo!", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Zkus menší číslo!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun newNumber() {
        secretNumber = Random.nextInt(1, 11)
    }
}