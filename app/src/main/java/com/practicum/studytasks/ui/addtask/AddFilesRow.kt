package com.practicum.studytasks.ui.addtask

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AudioFile
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Slideshow
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.theme.StudyTasksTheme
import com.practicum.studytasks.ui.ui_components.CustomTextField

private data class PickedFile(
    val uri: Uri,
    val name: String
)

@Composable
internal fun AddFilesRow() {
    val context = LocalContext.current
    var files by remember { mutableStateOf(listOf<PickedFile>()) }

    val duplicateToastText = stringResource(R.string.already_added_file)

    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri ?: return@rememberLauncherForActivityResult

        if (files.any { it.uri == uri }) {
            Toast.makeText(
                context,
                duplicateToastText,
                Toast.LENGTH_SHORT
            ).show()
            return@rememberLauncherForActivityResult
        }

        context.contentResolver.takePersistableUriPermission(
            uri,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )

        val name = context.contentResolver.query(uri, null, null, null, null)
            ?.use {
                val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                it.moveToFirst()
                it.getString(index)
            } ?: "file"

        files = files + PickedFile(uri, name)
    }

    LazyRow(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_between_cards_padding)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(files, key = { it.uri }) { file ->
            FileCard(
                file = file,
                onDelete = { files = files - file },
                onRename = { newName ->
                    files = files.map {
                        if (it.uri == file.uri)
                            PickedFile(it.uri, newName)
                        else it
                    }
                }
            )
        }

        // limit on the number of files
        if (files.size < 5) {
            item {
                AddButton {
                    picker.launch(arrayOf("*/*"))
                }
            }
        }
    }
}

@Composable
private fun FileCard(
    file: PickedFile,
    onDelete: () -> Unit,
    onRename: (String) -> Unit,
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .height(dimensionResource(R.dimen.add_file_button_size))
            .aspectRatio(3f / 2f)
            .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)))
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = file.uri
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)),
        border = BorderStroke(
            dimensionResource(R.dimen.card_border_width),
            MaterialTheme.colorScheme.outline
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .weight(0.7f)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    FilePreview(file.uri)
                }
                Box(
                    modifier = Modifier
                        .weight(0.3f)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .border(color = MaterialTheme.colorScheme.outline, width = dimensionResource(R.dimen.card_border_width))
                        .clickable { showDialog = true },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = file.name,
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.text_background_padding)),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.delete),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(dimensionResource(R.dimen.card_inner_padding))
                    .size(dimensionResource(R.dimen.small_icon_size))
                    .clickable { onDelete() },
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    if (showDialog) {
        RenameDialog(
            fullName = file.name,
            onDismiss = { showDialog = false },
            onConfirm = {
                onRename(it)
                showDialog = false
            }
        )
    }
}

@Composable
private fun RenameDialog(
    fullName: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    val dotIndex = fullName.lastIndexOf(".")
    val name = if (dotIndex != -1) fullName.substring(0, dotIndex) else fullName
    val extension = if (dotIndex != -1) fullName.substring(dotIndex) else ""

    var text by remember { mutableStateOf(name) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Text(
                text = stringResource(R.string.save),
                modifier = Modifier.clickable {
                    onConfirm(text + extension)
                }
            )
        },
        dismissButton = {
            Text(
                text = stringResource(R.string.cancel),
                modifier = Modifier.clickable { onDismiss() }
            )
        },
        title = { Text(
            text = stringResource(R.string.rename_file),
            fontWeight = FontWeight.SemiBold
            ) },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                CustomTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Text(
                    text = extension,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        titleContentColor = MaterialTheme.colorScheme.onBackground,
        textContentColor = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
private fun FilePreview(uri: Uri) {
    val context = LocalContext.current
    val mime = context.contentResolver.getType(uri) ?: ""

    when {
        // images
        mime.startsWith("image/") -> {
            AsyncImage(
                model = uri,
                contentDescription = mime,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // videos
        mime.startsWith("video/") -> {
            PreviewIcon(
                icon = Icons.Default.VideoLibrary,
                title = mime
            )
        }

        // PDF
        mime == "application/pdf" -> {
            PreviewIcon(
                icon = Icons.Default.PictureAsPdf,
                title = mime
            )
        }

        // tables
        mime == "application/vnd.ms-excel" ||
                mime == "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            -> {
            PreviewIcon(
                icon = Icons.Default.TableChart,
                title = mime
            )
        }

        // presentations
        mime == "application/vnd.ms-powerpoint" ||
                mime == "application/vnd.openxmlformats-officedocument.presentationml.presentation"
                     -> {
            PreviewIcon(
                icon = Icons.Default.Slideshow,
                title = mime
            )
        }

        // audio files
        mime.startsWith("audio/") -> {
            PreviewIcon(
                icon = Icons.Default.AudioFile,
                title = mime
            )
        }

        else -> {
            PreviewIcon(
                icon = Icons.Default.Description,
                title = mime
            )
        }
    }
}

@Composable
private fun PreviewIcon(
    icon: ImageVector = Icons.Default.Description,
    title: String? = null
) {
    Icon(
        imageVector = icon,
        contentDescription = title,
        modifier = Modifier.size(dimensionResource(R.dimen.large_icon_size)),
        tint = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun AddButton(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(dimensionResource(R.dimen.add_file_button_size))
            .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() },
        shape = RoundedCornerShape(dimensionResource(R.dimen.medium_rounded_corner)),
        border = BorderStroke(
            width = dimensionResource(R.dimen.card_border_width),
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_file),
                modifier = Modifier.size(dimensionResource(R.dimen.large_icon_size)),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun FileCardPreview_Light() {
    StudyTasksTheme {
        FileCard(
            file = PickedFile(
                uri = Uri.EMPTY,
                name = "Очень_длинное_название_файла.pdf"
            ),
            onDelete = {},
            onRename = {}
        )
    }
}

@Preview(showBackground = true, locale = "ru", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FileCardPreview_Dark() {
    StudyTasksTheme {
        FileCard(
            file = PickedFile(
                uri = Uri.EMPTY,
                name = "Фото.jpg"
            ),
            onDelete = {},
            onRename = {}
        )
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun AddButtonPreview() {
    StudyTasksTheme {
        AddButton {}
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun AddFilesRowPreview() {
    StudyTasksTheme {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.large_between_cards_padding)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_between_cards_padding))
        ) {
            FileCard(
                file = PickedFile(Uri.EMPTY, "Документ.pdf"),
                onDelete = {},
                onRename = {}
            )
            FileCard(
                file = PickedFile(Uri.EMPTY, "Текст.txt"),
                onDelete = {},
                onRename = {}
            )
            AddButton {}
        }
    }
}

