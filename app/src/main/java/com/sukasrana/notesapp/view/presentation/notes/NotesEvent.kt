package com.sukasrana.notesapp.view.presentation.notes

sealed interface NotesEvent {
    data class OnTitleChange(val title: String): NotesEvent
    data class OnDescriptionChange(val description: String): NotesEvent
    data class OnDateChange(val date: Long?): NotesEvent
    data class OnGetNotesById(val id: Int): NotesEvent
}