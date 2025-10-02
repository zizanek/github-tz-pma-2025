package com.example.myapp003objednavka

import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp003objednavka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        title = "Objednávka kola"

        // binding settings
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOrder.setOnClickListener {
            // načtení ID vybraného radioButtonu z radioGroup
            val bikeRbId = binding.rgBikes.checkedRadioButtonId
            val bike = findViewById<RadioButton>(bikeRbId)

            val fork = binding.cbFork.isChecked
            val saddle = binding.cbSaddle.isChecked
            val handleBar  = binding.cbHandleBar.isChecked

            val orderText = "Souhrn objednávky: " + "${bike.text}" +
                    (if(fork) "; lepší vidlice" else "") +
                    (if(saddle) "; lepší sedlo" else "") +
                    (if(handleBar) "; lepší říditka" else "")

            binding.tvOdrer.text = orderText
        }

        // Změna obrázku v závislosti na vybraném radioButtonu
        binding.rbBike1.setOnClickListener {
            binding.ivBike.setImageResource(R.drawable.oiz_m10_tr)
        }

        binding.rbBike2.setOnClickListener {
            binding.ivBike.setImageResource(R.drawable.oiz_m20_tr)
        }

        binding.rbBike3.setOnClickListener {
            binding.ivBike.setImageResource(R.drawable.oiz_m30_tr)
        }
    }
}