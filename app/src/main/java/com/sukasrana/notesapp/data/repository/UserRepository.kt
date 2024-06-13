package com.sukasrana.notesapp.data.repository

import com.sukasrana.notesapp.data.remote.response.User
import com.sukasrana.notesapp.data.remote.retrofit.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
}
