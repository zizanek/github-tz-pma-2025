package com.example.myapp007afragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp007afragments.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFragment1.setOnClickListener {
            replaceFragment(Fragment1())
        }

        binding.btnFragment2.setOnClickListener {
            replaceFragment(Fragment2())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        // získání instance správce fragmentu
        val fragmentManager = supportFragmentManager

        // vyvoření nové transakce pro fragmenty
        val fragmentTransaction = fragmentManager.beginTransaction()

        // nahrazení fragmentu ve view kontejneru novým fragmentem, který byl předán jako argument
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)

        // potvrzení transakce a provedení výměny fragmentů
        fragmentTransaction.commit()
    }
}