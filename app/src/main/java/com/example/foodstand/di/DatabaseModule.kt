package com.example.foodstand.di

import android.content.Context
import androidx.room.Room
import com.example.foodstand.data.database.database.RecipesDao
import com.example.foodstand.data.database.database.RecipesDatabase
import com.example.foodstand.data.repository.local.LocalRepository
import com.example.foodstand.data.repository.local.LocalRepositoryImpl
import com.example.foodstand.util.Constants.RECIPES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
        context,
        RecipesDatabase::class.java,
        RECIPES_NAME
        ).build()

    @Singleton
    @Provides
    fun provideDao(database: RecipesDatabase) =
        database.recipesDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(dao: RecipesDao): LocalRepository =
        LocalRepositoryImpl(dao)

}