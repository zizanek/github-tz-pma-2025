package com.example.myapp012amynotehub.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    // CREATE - Vloží novou poznámku do databáze
    @Insert
    suspend fun insert(note: Note)

    // READ - Načte všechny poznámky a vrátí je jako Flow, které umožňuje pozorování změn
    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNotes(): Flow<List<Note>>

    // UPDATE - Aktualizuje existující poznámku
    @Update
    suspend fun update(note: Note)

    // DELETE - Smaže poznámku
    @Delete
    suspend fun delete(note: Note)

}