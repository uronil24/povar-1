package com.example.povar.presentation.profile

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.telephony.mbms.MbmsErrors.DownloadErrors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.povar.domain.usecase.proto.GetProfileUseCase
import com.example.povar.presentation.mapper.ProfileItemDomainMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val mapper: ProfileItemDomainMapper
) : ViewModel() {
    private val _state = MutableProfileScreenState()
    val state = _state as ProfileScreenState

    init {
        load()
    }

    fun download(
        url: Uri,
        context: Context
    ) {
        try {
            val request: DownloadManager.Request = DownloadManager.Request(url)
            with(request) {
                setTitle("Profile")
                setMimeType("mimi")
                setDescription("Profile")
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    NAME_FILE
                )
            }
            val manager: DownloadManager =
                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun open(context: Context){
        try {
            val file = File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS
                ),
                NAME_FILE
            )
            val uri = FileProvider.getUriForFile(
                context,
                context.applicationContext?.packageName + ".provider",
                file
            )
            val intent =
                Intent(Intent.ACTION_VIEW)
            with(intent) {
                setDataAndType(
                    uri,
                    "application/pdf"
                )
                flags =
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun load() {
        viewModelScope.launch {
            getProfileUseCase.getProfile().collect {
                mapper.toItem(it).apply {
                    _state.name = name
                    _state.avatarUri = avatarUri
                    _state.resumeUri = resumeUri
                }
            }
        }
    }

    companion object{
        const val NAME_FILE = "profile.pdf"
    }

    private class MutableProfileScreenState : ProfileScreenState{
        override var name: String by mutableStateOf("")
        override var resumeUri: Uri by mutableStateOf(Uri.parse("https://elibrary.ru/download/elibrary_44394445_69180276.pdf"))
        override var avatarUri: Uri by mutableStateOf(Uri.EMPTY)
    }
}