package com.sukasrana.notesapp.data.remote.retrofit

import com.sukasrana.notesapp.data.remote.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<UserResponse>
}