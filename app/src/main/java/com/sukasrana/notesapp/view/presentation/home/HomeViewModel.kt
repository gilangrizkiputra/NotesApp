package com.sukasrana.notesapp.view.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukasrana.notesapp.domain.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val notesRepository: NotesRepository): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getAllNotes()
    }

    private fun getAllNotes() = viewModelScope.launch(Dispatchers.IO){
        notesRepository.getAllNotes().collect{ notes ->
            _state.update {
                it.copy(notes = notes)
            }
        }
    }
}