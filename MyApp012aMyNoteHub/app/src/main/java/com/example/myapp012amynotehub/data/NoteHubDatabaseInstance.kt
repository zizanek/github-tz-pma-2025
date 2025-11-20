package com.example.myapp012amynotehub.data

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

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
     * MIGRACE z verze 1 na 2:
     * Přidáme dva nové sloupce:
     * - createdAt (Long)
     * - category (String)
     */
    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Přidání createdAt (Long) – nesmí být NULL, proto DEFAULT
            db.execSQL(
                "ALTER TABLE note_table ADD COLUMN createdAt INTEGER NOT NULL DEFAULT 0"
            )

            // Přidání category (String) – nesmí být NULL, proto DEFAULT
            db.execSQL(
                "ALTER TABLE note_table ADD COLUMN category TEXT NOT NULL DEFAULT 'General'"
            )
        }
    }

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
                .addMigrations(MIGRATION_1_2)
                .build()
            INSTANCE = instance
            instance
        }
    }
}