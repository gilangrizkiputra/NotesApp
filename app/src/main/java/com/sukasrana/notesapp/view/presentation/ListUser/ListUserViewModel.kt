package com.sukasrana.notesapp.view.presentation.ListUser

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukasrana.notesapp.data.remote.response.User
import com.sukasrana.notesapp.data.repository.UserRepository
import com.sukasrana.notesapp.domain.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            val response = getUsersUseCase()
            if (response.isSuccessful) {
                _users.value = response.body()?.data
            } else {
                Log.e("ListUserViewModel", "Response Failed: ${response.message()}")

            }
        }
    }
}
