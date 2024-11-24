package com.example.povar.presentation.profile

import android.app.DownloadManager
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ModeEditOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.povar.R
import com.example.povar.presentation.navigation.BottomBarModel
import com.example.povar.presentation.receiver.ScreenReceiver

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onToEdit: () -> Unit,
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current

    ScreenReceiver(
        systemAction = ACCESS,
        onSystemEvent = { intent ->
            if (intent?.action == ACCESS) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
                if (id != -1L) {
                    viewModel.open(context)
                }
            }
        })

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = BottomBarModel.Profile.title ?: "Нет названия"
                        )
                    },
                    actions = {
                        IconButton(onClick = { onToEdit() }) {
                            Icon(
                                imageVector = Icons.Filled.ModeEditOutline,
                                contentDescription = null
                            )
                        }
                    }
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfilePicture(
                    avatarUrl = state.avatarUri,
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProfileInfo(state.name)

                Spacer(modifier = Modifier.height(24.dp))

                DownloadResumeButton(onClickDownload = {
                    viewModel.download(state.resumeUri, context = context)
                })
            }
        }
    }
}

@Composable
fun ProfilePicture(
    avatarUrl: Uri?,
) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = avatarUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(128.dp),
            error = painterResource(R.drawable.autorenew)
        )
    }
}

@Composable
fun ProfileInfo(
    name: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun DownloadResumeButton(
    onClickDownload: () -> Unit
) {
    Button(
        onClick = {
            onClickDownload()
        },
        modifier = Modifier.width(200.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Text(
            text = "Скачать резюме",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen({})
}

private const val ACCESS = "android.intent.action.DOWNLOAD_COMPLETE"