package com.example.povar.presentation.editProfile

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.povar.domain.usecase.proto.GetProfileUseCase
import com.example.povar.domain.usecase.proto.SetProfileUseCase
import com.example.povar.presentation.mapper.ProfileItemDomainMapper
import com.example.povar.presentation.model.ProfileItem
import com.example.povar.presentation.receiver.NotifyReceiver
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class EditProfileScreenViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val setProfileUseCase: SetProfileUseCase,
    private val mapper: ProfileItemDomainMapper,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _state = MutableEditProfileState()
    val state = _state as EditProfileScreenState

    private val format = DateTimeFormatter.ofPattern("HH:mm")

    init {
        load()
        _state.isNeedToShowPermission = true
    }

    fun updateName(input: String) {
        _state.name = input
    }

    fun updateAvatarUri(uri: Uri) {
        _state.avatarUri = uri
    }

    fun updateResumeUri(input: String) {
        _state.resumeUri = Uri.parse(input)
    }

    fun updateTime(input: String) {
        _state.timeString = input
        validateTime()
    }

    fun updateTime(hour: Int, minute: Int) {
        _state.time = LocalTime.of(hour, minute)
        _state.timeString = _state.time.format(format)
        _state.timeError = null
    }

    fun changeOpennedTimer() {
        _state.isNeedToOpenTimer = !state.isNeedToOpenTimer
    }

    fun onClosePermission() {
        _state.isNeedToShowPermission = false
    }

    fun onSelectPhoto() {
        _state.isNeedToShowSelect = true
    }

    fun onSelectDismiss() {
        _state.isNeedToShowSelect = false
    }

    fun save(back: () -> Unit) {
        validateTime()
        if (_state.timeError == null) {
            viewModelScope.launch {
                val profile = ProfileItem(
                    name = state.name,
                    avatarUri = state.avatarUri,
                    resumeUri = state.resumeUri
                )
                setProfileUseCase.setProfile(mapper.toDomain(profile))
                setAlarmManager()
                back()
            }
        }
    }

    private fun setAlarmManager() {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val dateTime = LocalDateTime.of(LocalDate.now(), _state.time)
        val timeInMillis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val notifyIntent = Intent(context, NotifyReceiver::class.java)

        notifyIntent.putExtras(
            Bundle().apply {
                putString("NOTIFY", "Имя пользователя: ${_state.name}")
            }
        )

        val notifyPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            notifyIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        try {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                notifyPendingIntent
            )
        } catch (e: SecurityException) {
            Log.e("alarmManager", "Failed to set reminder")
        }
    }

    private fun validateTime() {
        try {
            _state.time = LocalTime.parse(_state.timeString, format)
            _state.timeError = null
        } catch (e: Exception) {
            _state.timeError = "Неверный ввод"
        }
    }

    private fun load() {
        viewModelScope.launch {
            getProfileUseCase.getProfile().collect {
                val profile = mapper.toItem(it)
                _state.name = profile.name
                _state.avatarUri = profile.avatarUri
                _state.resumeUri = profile.resumeUri
            }
        }
    }

    private class MutableEditProfileState : EditProfileScreenState {
        override var name by mutableStateOf("")
        override var avatarUri: Uri by mutableStateOf(Uri.EMPTY)
        override var resumeUri: Uri by mutableStateOf(Uri.parse("https://elibrary.ru/download/elibrary_44394445_69180276.pdf"))
        override var time: LocalTime by mutableStateOf(LocalTime.now())
        override var timeString: String by mutableStateOf("")
        override var timeError: String? by mutableStateOf(null)
        override var isNeedToShowPermission by mutableStateOf(false)
        override var isNeedToShowSelect by mutableStateOf(false)
        override var isNeedToOpenTimer: Boolean by mutableStateOf(false)
    }
}