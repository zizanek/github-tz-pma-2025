package com.example.myapp009aimagetoapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp009aimagetoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
            uri: Uri? -> binding.ivImage.setImageURI(uri)
        }

        binding.btnTakeImage.setOnClickListener {
            // Tento příkaz spustí výběr obrázku z telefonu
            // Vybraný obrázek se zobrazí v ImageView
            getContent.launch("image/*")
        }
    }
}