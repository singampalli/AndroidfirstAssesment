package com.example.userslistassessment.api

import com.example.userslistassessment.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // Define an endpoint with pagination parameters
    @GET("users")
    fun getUsers(@Query("page") page: Int, @Query("size") size: Int): Call<List<User>>

}
