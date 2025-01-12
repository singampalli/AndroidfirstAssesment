package com.example.androidfirstassignment.network

import retrofit2.http.GET
import com.example.androidfirstassignment.model.User

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}
