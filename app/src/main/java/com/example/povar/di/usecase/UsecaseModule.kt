package com.example.povar.di.usecase

import com.example.povar.domain.repo.PreferencesRepository
import com.example.povar.domain.repo.ProfileRepository
import com.example.povar.domain.repo.RecipesRepository
import com.example.povar.domain.usecase.preference.GetPreferencesUseCase
import com.example.povar.domain.usecase.preference.GetPreferencesUseCaseImpl
import com.example.povar.domain.usecase.preference.SetPreferencesUseCase
import com.example.povar.domain.usecase.preference.SetPreferencesUseCaseImpl
import com.example.povar.domain.usecase.proto.GetProfileUseCase
import com.example.povar.domain.usecase.proto.GetProfileUseCaseImpl
import com.example.povar.domain.usecase.proto.SetProfileUseCase
import com.example.povar.domain.usecase.proto.SetProfileUseCaseImpl
import com.example.povar.domain.usecase.recipe.DeleteRecipeUseCase
import com.example.povar.domain.usecase.recipe.DeleteRecipeUseCaseImpl
import com.example.povar.domain.usecase.recipe.GetRecipesUseCase
import com.example.povar.domain.usecase.recipe.GetRecipesUseCaseImpl
import com.example.povar.domain.usecase.recipe.SaveRecipeUseCase
import com.example.povar.domain.usecase.recipe.SaveRecipeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {
    @Singleton
    @Provides
    fun getRecipesUseCaseProvide(
        repo: RecipesRepository
    ): GetRecipesUseCase {
        return GetRecipesUseCaseImpl(repo)
    }

    @Singleton
    @Provides
    fun saveRecipeUseCaseProvide(
        repo: RecipesRepository
    ): SaveRecipeUseCase {
        return SaveRecipeUseCaseImpl(repo)
    }

    @Singleton
    @Provides
    fun deleteRecipeUseCaseProvide(
        repo: RecipesRepository
    ): DeleteRecipeUseCase {
        return DeleteRecipeUseCaseImpl(repo)
    }

    @Singleton
    @Provides
    fun setPreferencesUseCaseProvide(
        repo: PreferencesRepository
    ): SetPreferencesUseCase {
        return SetPreferencesUseCaseImpl(repo)
    }

    @Singleton
    @Provides
    fun getPreferencesUseCaseProvide(
        repo: PreferencesRepository
    ): GetPreferencesUseCase {
        return GetPreferencesUseCaseImpl(repo)
    }

    @Singleton
    @Provides
    fun getProfileUseCaseProvide(
        repo: ProfileRepository
    ): GetProfileUseCase {
        return GetProfileUseCaseImpl(repo)
    }

    @Singleton
    @Provides
    fun setProfileUseCaseProvide(
        repo: ProfileRepository
    ): SetProfileUseCase {
        return SetProfileUseCaseImpl(repo)
    }
}