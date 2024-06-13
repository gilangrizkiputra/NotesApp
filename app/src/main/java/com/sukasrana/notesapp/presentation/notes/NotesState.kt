package com.sukasrana.notesapp.presentation.notes

data class NotesState(
    val title: String = "",
    val description: String = "",
    val isDatePickerDialogOpen: Boolean = false,
    val dueDate: Long? = null,
    val currentNoteskId: Int? = null,
)