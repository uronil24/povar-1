package com.example.povar.data.storage.util

import androidx.datastore.core.Serializer
import com.example.povar.data.storage.model.ProfileDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object ProfileSerializer : Serializer<ProfileDb> {
    override val defaultValue: ProfileDb
        get() = ProfileDb()

    override suspend fun readFrom(input: InputStream): ProfileDb {
        return try {
            Json.decodeFromString(
                deserializer = ProfileDb.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException){
            ProfileDb()
        }
    }

    override suspend fun writeTo(t: ProfileDb, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = ProfileDb.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}