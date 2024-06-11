package com.sukasrana.notesapp.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sukasrana.notesapp.di.Injection
import com.sukasrana.notesapp.domain.repository.NotesRepository
import com.sukasrana.notesapp.view.presentation.home.HomeViewModel
import com.sukasrana.notesapp.view.presentation.notes.NotesViewModel

class ViewModelFactory private constructor(private val notesRepository: NotesRepository) :
ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideRepository(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(notesRepository) as T
            }
            modelClass.isAssignableFrom(NotesViewModel::class.java) -> {
                NotesViewModel(notesRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class : " + modelClass.name)
        }

}