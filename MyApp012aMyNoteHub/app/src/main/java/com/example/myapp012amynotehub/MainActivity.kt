package com.example.myapp012amynotehub

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp012amynotehub.data.Note
import com.example.myapp012amynotehub.data.NoteDao
import com.example.myapp012amynotehub.data.NoteHubDatabaseInstance
import com.example.myapp012amynotehub.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteDao: NoteDao
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchQuery = MutableStateFlow("")

        // Po kliknutí na FAB otevře AddNoteActivity
        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        /* Zobrazování poznámek */

        // 1) Vytvoření adaptéru
        //val adapter = NoteAdapter()
        adapter = NoteAdapter(
            onEditClick = { note ->
                val intent = Intent(this, EditNoteActivity::class.java)
                intent.putExtra("note_id", note.id)
                startActivity(intent)
            },
            onDeleteClick = { note ->
                deleteNote(note)
            }
        )


        // 2) Nastavení RecyclerView
        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewNotes.adapter = adapter

        // 3) Získání DAO
        //val noteDao = NoteHubDatabaseInstance.getDatabase(applicationContext).noteDao()
        noteDao = NoteHubDatabaseInstance.getDatabase(this).noteDao()

        // 4) Pozorování dat pomocí Flow z Room
        // Spustí korutinu v rámci životního cyklu aktivity.
        // Když se aktivita zničí, korutina se automaticky ukončí.
        lifecycleScope.launch {
            // Sleduje Flow. Kdykoli se změní data v DB → seznam se okamžitě obnoví.
            noteDao.getAllNotes().collectLatest { notes ->
                //Pošle nová data do adapteru, aby se překreslil RecyclerView.
                adapter.submitList(notes)
            }
        }

        // --- Vyhledávání v poznámkách ---
        // Listener SearchView: změna textu → změní hodnotu Flow
        binding.searchViewNotes.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                searchQuery.value = newText ?: ""
                return true
            }
        })

        // Jeden společný collector, který reaguje na změnu textu
        lifecycleScope.launch {
            searchQuery.collectLatest { text ->
                if (text.isEmpty()) {
                    // Zobrazit všechny poznámky
                    noteDao.getAllNotes().collectLatest { notes ->
                        adapter.submitList(notes)
                    }
                } else {
                    // Zobrazit filtrované poznámky
                    noteDao.searchNotes(text).collectLatest { notes ->
                        adapter.submitList(notes)
                    }
                }
            }
        }





    }
    private fun deleteNote(note: Note) {
        // spustí se asynchronní úkol na vlákně určeném pro práci s databází
        // a ukončí se automaticky, když se aktivita zničí
        lifecycleScope.launch(Dispatchers.IO) {
            noteDao.delete(note)
        }
    }
}