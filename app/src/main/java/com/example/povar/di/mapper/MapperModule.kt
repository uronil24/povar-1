package com.example.povar.di.mapper

import com.example.povar.data.storage.mapper.ProfileDomainDbMapper
import com.example.povar.data.storage.mapper.ProfileDomainDbMapperImpl
import com.example.povar.data.storage.mapper.RecipeDomainDbMapper
import com.example.povar.data.storage.mapper.RecipeDomainDbMapperImpl
import com.example.povar.presentation.mapper.ProfileItemDomainMapper
import com.example.povar.presentation.mapper.ProfileItemDomainMapperImpl
import com.example.povar.presentation.mapper.RecipeItemDomainMapper
import com.example.povar.presentation.mapper.RecipeItemDomainMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Singleton
    @Provides
    fun recipeDomainDbMapperProvide() : RecipeDomainDbMapper{
        return RecipeDomainDbMapperImpl()
    }

    @Singleton
    @Provides
    fun recipeItemDomainMapperImpl() : RecipeItemDomainMapper{
        return RecipeItemDomainMapperImpl()
    }

    @Singleton
    @Provides
    fun profileItemDomainMapperProvide() : ProfileItemDomainMapper{
        return ProfileItemDomainMapperImpl()
    }

    @Singleton
    @Provides
    fun profileDomainDbMapperProvide() : ProfileDomainDbMapper{
        return ProfileDomainDbMapperImpl()
    }
}