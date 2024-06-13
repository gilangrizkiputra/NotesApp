package com.sukasrana.notesapp.presentation.home

import com.sukasrana.notesapp.data.local.entity.NotesEntity

data class HomeState(
    val notes: List<NotesEntity> = emptyList()
)
