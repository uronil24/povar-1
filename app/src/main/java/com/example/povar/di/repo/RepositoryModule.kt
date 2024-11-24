package com.example.povar.di.repo

import com.example.povar.data.storage.repo.PreferencesRepositoryImpl
import com.example.povar.data.storage.repo.ProfileRepositoryImpl
import com.example.povar.data.storage.repo.RecipesRepositoryImpl
import com.example.povar.domain.repo.PreferencesRepository
import com.example.povar.domain.repo.ProfileRepository
import com.example.povar.domain.repo.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRecipesRepository(
        impl: RecipesRepositoryImpl
    ): RecipesRepository

    @Binds
    abstract fun bindPreferencesRepository(
        impl: PreferencesRepositoryImpl
    ): PreferencesRepository

    @Binds
    abstract fun bindProfileRepository(
        impl: ProfileRepositoryImpl
    ): ProfileRepository
}