package com.example.myapp012amynotehub.data

import androidx.room.Database
import androidx.room.RoomDatabase
import java.util.Locale

@Database(
    entities = [Note::class],
    version = 2,
    exportSchema = false
)

abstract class NoteHubDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}