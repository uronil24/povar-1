package com.example.povar.data.storage.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.povar.data.storage.model.RecipeDb
import com.example.povar.data.storage.util.CustomTypeConverters

@Database(
    entities = [
        RecipeDb::class
    ],
    version = 1
)
@TypeConverters(CustomTypeConverters::class)
abstract class RecipeDatabase: RoomDatabase(){
    abstract fun RecipeDao(): RecipeDao
}