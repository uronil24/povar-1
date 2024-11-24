package com.example.povar.di.dao

import android.content.Context
import androidx.room.Room
import com.example.povar.data.storage.dao.RecipeDao
import com.example.povar.data.storage.dao.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Singleton
    @Provides
    fun provideRecipeDatabase(@ApplicationContext context: Context) : RecipeDatabase =
        Room.databaseBuilder(
            context,
            RecipeDatabase::class.java,
            "Recipe.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideRecipeDao(appDatabase: RecipeDatabase): RecipeDao{
        return appDatabase.RecipeDao()
    }
}