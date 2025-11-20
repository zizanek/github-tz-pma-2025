package com.example.myapp012amynotehub.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // ID poznámky, automaticky generované
    val title: String, // Název poznámky
    val content: String, // Obsah poznámky

    // nový atribut 1 — timestamp
    val createdAt: Long = System.currentTimeMillis(),
    // nový atribut 2 — kategorie
    val category: String = "General"
)
