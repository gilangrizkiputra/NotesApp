package com.sukasrana.notesapp.data.local.room

import android.app.TaskInfo
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sukasrana.notesapp.data.local.entity.NotesEntity

@Database(entities = [NotesEntity::class], version = 2, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun NotesDao(): NotesDao

    companion object{
        private var INSTANCE: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase =
            INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,
                    "db_notes"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}