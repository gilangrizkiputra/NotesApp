package com.sukasrana.notesapp.data.repository

import com.sukasrana.notesapp.data.local.LocalDataSource
import com.sukasrana.notesapp.data.local.entity.NotesEntity
import com.sukasrana.notesapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl private constructor(
    private val localDataSource: LocalDataSource
): NotesRepository {

    companion object{
        @Volatile
        private var instance: NotesRepositoryImpl? = null

        fun getInstance(
            localData: LocalDataSource,
        ): NotesRepositoryImpl =
            instance ?: synchronized(this){
                instance ?: NotesRepositoryImpl(localData)
            }
    }

    override suspend fun upsertNotes(notesEntity: NotesEntity) = localDataSource.upsertNotes(notesEntity)

    override suspend fun deleteNotes(notesId: Int) = localDataSource.deleteNotesById(notesId)

    override fun getNotesById(notesId: Int): Flow<NotesEntity?> = localDataSource.getNotesById(notesId)

    override fun getAllNotes(): Flow<List<NotesEntity>> = localDataSource.getAllNotes()
}