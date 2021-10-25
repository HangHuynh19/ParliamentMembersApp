//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 1 October 2021

package com.example.parliamentmembersapp.network

import com.example.parliamentmembersapp.database.MemberOfParliament
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// This file is responsible for fetching the data for the predetermined link using retrofit.
// Then, the JSON acquired from the get method is parsed by moshi

private const val BASE_URL = "https://users.metropolia.fi/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ParliamentApiService {
    @GET("~thanhhu/mps.json")
    suspend fun getParliamentMembers(): List<MemberOfParliament>
}

object ParliamentApi {
    val retrofitService : ParliamentApiService by lazy { retrofit.create(ParliamentApiService::class.java) }
}