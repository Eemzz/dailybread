package com.example.dailybread.retrofit

import com.example.dailybread.user.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://10.0.2.2:3000/auth/";
object Retro {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                //.addHeader("Authorization", AUTH)
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: ApiInterface by lazy {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

            retrofitBuilder.create(ApiInterface::class.java)
    }

    /*fun getMyData() {
        val retrofitData = retrofitBuilder.getData();

        retrofitData.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                TODO("Not yet implemented")
                val responseBody = response.body()!!

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }*/




}