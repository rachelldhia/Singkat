package com.example.singkat.api


import com.example.singkat.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login") // Ganti dengan endpoint login sesuai dengan API Anda
    fun login(
        @Field("username") username: String,
        @Field("iduser") id: String,
        @Field("password") password: String
    ): Call<LoginResponse>

}