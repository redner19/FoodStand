package com.example.foodstand.di

import com.example.foodstand.data.repository.remote.RemoteRepository
import com.example.foodstand.data.repository.remote.RemoteRepositoryImpl
import com.example.foodstand.data.service.FoodStandService
import com.example.foodstand.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    //This will provide Retrofit
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    //This will provide OkHttpClient
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    //This will provide the FoodStandService
    fun providesFoodStandService(retrofit: Retrofit): FoodStandService =
        retrofit.create(FoodStandService::class.java)

    @Provides
    @Singleton
    //This will provide the FoodStandRepositoryImpl
    fun providesFoodStandRepository(fss: FoodStandService): RemoteRepository =
        RemoteRepositoryImpl(fss)

}