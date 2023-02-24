package com.sardordev.burgerapp.di

import com.sardordev.burgerapp.data.api.BurgerApi
import com.sardordev.burgerapp.domain.BurgerRepository
import com.sardordev.burgerapp.domain.BurgerRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun getBurgersApi(): BurgerApi {
        return Retrofit.Builder()
            .baseUrl("https://burgers-hub.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BurgerApi::class.java)
    }


    @Singleton
    @Provides
    fun getBurgerRepository(api: BurgerApi): BurgerRepository = BurgerRepositoryImp(api)


}