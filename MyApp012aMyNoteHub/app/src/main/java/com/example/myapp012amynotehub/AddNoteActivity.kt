package com.example.myapp012amynotehub

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp012amynotehub.data.Note
import com.example.myapp012amynotehub.data.NoteHubDatabaseInstance
import com.example.myapp012amynotehub.databinding.ActivityAddNoteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = NoteHubDatabaseInstance.getDatabase(this)
        val noteDao = db.noteDao()

        binding.btnSaveNote.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val content = binding.editTextContent.text.toString()

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Vyplň název i obsah", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val note = Note(title = title, content = content)

            // Spustí korutinu na vlákně určeném pro vstupně/výstupní operace (IO),
            // které jsou vhodné pro práci s databází, aby se nezablokovalo UI.
            CoroutineScope(Dispatchers.IO).launch {
                // Uložení poznámky do databáze na vlákně pro IO operace (nezablokuje UI)
                noteDao.insert(note)

                // Po uložení zavře tuto aktivitu a vrátí se zpět na hlavní obrazovku
                finish()
            }
        }



        



    }
}