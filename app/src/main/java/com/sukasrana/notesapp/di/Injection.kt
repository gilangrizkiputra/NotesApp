package com.sukasrana.notesapp.di

import android.content.Context
import com.sukasrana.notesapp.data.local.LocalDataSource
import com.sukasrana.notesapp.data.local.room.NotesDatabase
import com.sukasrana.notesapp.data.repository.NotesRepositoryImpl
import com.sukasrana.notesapp.domain.repository.NotesRepository

object Injection {

    fun provideRepository(context: Context): NotesRepository{
        val database = NotesDatabase.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.NotesDao())

        return NotesRepositoryImpl.getInstance(localDataSource)
    }
}