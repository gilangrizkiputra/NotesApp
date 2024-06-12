package com.sukasrana.notesapp.data.local.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.sukasrana.notesapp.data.local.entity.NotesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Upsert
    suspend fun upsertNotes(notesEntity: NotesEntity)

    @Query("DELETE FROM notes WHERE notes_id = :notesId")
    suspend fun deleteNotesById(notesId: Int)

    @Query("SELECT * FROM notes WHERE notes_id = :notesId")
    fun getNotesId(notesId: Int): Flow<NotesEntity?>

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<NotesEntity>>
}