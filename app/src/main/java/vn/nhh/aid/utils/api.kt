package vn.nhh.aid.utils

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

var retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .build()
//
//
//interface ApiService {
//    @GET("users/{user}/repos")
//    fun listRepos(@Path("user") user: String?): Call<List<Repo?>?>?
//}