package com.sardordev.burgerapp.data.api

import com.sardordev.burgerapp.data.model.BurgerModel
import com.sardordev.burgerapp.data.model.BurgerModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface BurgerApi {


    @GET("burgers")
    @Headers("X-RapidAPI-Key:be728f94afmsh71892bf362691afp1339efjsnf610ff42e457","X-RapidAPI-Host:burgers-hub.p.rapidapi.com")
    suspend fun getburgersList(): Response<List<BurgerModelItem>>




}