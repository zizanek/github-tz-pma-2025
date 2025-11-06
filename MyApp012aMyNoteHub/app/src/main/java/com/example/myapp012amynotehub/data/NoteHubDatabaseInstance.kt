package com.example.myapp012amynotehub.data

import android.content.Context
import androidx.room.Room

/**
 * Objekt, který zajišťuje, že v aplikaci bude existovat
 * pouze jedna instance databáze (tzv. Singleton).
 */

object NoteHubDatabaseInstance {

    /*@Volatile znamená, že proměnná je viditelná pro všechna vlákna okamžitě. */
    /* V praxi: když se v jednom vlákně změní hodnota (např. INSTANCE databáze),
       ostatní vlákna uvidí tu novou hodnotu hned — ne až po zpoždění z cache paměti. */
    @Volatile
    private var INSTANCE: NoteHubDatabase? = null

    /**
     * Vrátí instanci databáze. Pokud ještě neexistuje, vytvoří ji.
     */
    fun getDatabase(context: Context): NoteHubDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteHubDatabase::class.java,
                "notehub_database"
            )
                .build()
            INSTANCE = instance
            instance
        }
    }
}