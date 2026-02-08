package com.practicum.studytasks.ui.addtask.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.NoteAdd
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TableRows
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.practicum.studytasks.R
import com.practicum.studytasks.ui.addtask.AddFilesRow
import com.practicum.studytasks.ui.theme.StudyTasksTheme
import com.practicum.studytasks.ui.addtask.CustomDatePicker
import com.practicum.studytasks.ui.addtask.NumberOfTasksRow
import com.practicum.studytasks.ui.addtask.SwitchRow
import com.practicum.studytasks.ui.addtask.TaskParamsHeader
import com.practicum.studytasks.ui.addtask.CustomDropdown
import com.practicum.studytasks.ui.ui_components.CustomTextField

@Composable
fun AddTaskDialog(
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(R.dimen.dialog_horizontal_outer_padding),
                    vertical = dimensionResource(R.dimen.dialog_vertical_outer_padding)
                ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.large_rounded_corner)),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
        ) {
            AddTaskDialogContent(onDismiss)
        }
    }
}

@Composable
private fun AddTaskDialogContent(
    onDismiss: () -> Unit
) {
    val notSelectedText = stringResource(R.string.not_selected_variant)
    var taskType by remember { mutableStateOf(notSelectedText) }
    var subject by remember { mutableStateOf(notSelectedText) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                })
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.dialog_inner_padding))
        ) {

            // header
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.add_task),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    Modifier
                        .size(dimensionResource(R.dimen.cancel_button_size))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.small_rounded_corner)))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                color = MaterialTheme.colorScheme.onBackground
                            ),
                            onClick = onDismiss
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close)
                    )
                }
            }

            Spacer(Modifier.height(dimensionResource(R.dimen.large_between_header_content_padding)))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_between_header_content_padding))
            ) {
                TaskParamsHeader(
                    icon = Icons.Filled.Description, // TextSnippet
                    title = stringResource(R.string.task_title)
                )
                CustomTextField(
                    placeholder = stringResource(R.string.enter_title)
                )

                TaskParamsHeader(title = stringResource(R.string.task_type))
                CustomDropdown(
                    initValue = stringResource(R.string.not_selected_variant),
                    value = taskType,
                    onValueChange = { taskType = it },
                    listOf(
                        "Домашнее задание",
                        "Практическая работа",
                        "Лабораторная работа"
                    ) // добавить возможность менять типы задач
                )

                TaskParamsHeader(
                    icon = Icons.AutoMirrored.Filled.MenuBook,
                    title = stringResource(R.string.subject)
                )
                CustomDropdown(
                    initValue = stringResource(R.string.not_selected_variant),
                    value = subject,
                    onValueChange = { subject = it },
                    items = listOf(
                        "Анализ и моделирование промышленных систем",
                        "Киберфизические системы в производстве",
                        "Основы проектирования автоматизированных систем реального времени",
                        "Промышленная информатика",
                        "Промышленный интернет",
                        "Средства автоматизации технологических систем",
                        "Технологическое оборудование автоматизированных производств",
                        "Управление разработкой и реализацией проекта"
                    ) // добавить возможность менять предметы
                )

                SwitchRow(
                    icon = Icons.Default.Schedule,
                    title = stringResource(R.string.set_deadline)
                ) {
                    var date by remember { mutableStateOf("") }

                    CustomDatePicker(
                        selectedDate = date,
                        onDateSelected = { date = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                SwitchRow(
                    icon = Icons.Filled.FileOpen,
                    title = stringResource(R.string.files)
                ) {
                    AddFilesRow()
                }


                SwitchRow(
                    icon = Icons.AutoMirrored.Filled.NoteAdd,
                    title = stringResource(R.string.description)
                ) {
                    var description by remember { mutableStateOf("") }

                    CustomTextField(
                        value = description,
                        onValueChange = { description = it },
                        placeholder = stringResource(R.string.description_hint),
                        singleLine = false
                    )
                }

                SwitchRow(
                    icon = Icons.Filled.TableRows,
                    title = stringResource(R.string.number_of_tasks)
                ) {
                    NumberOfTasksRow()
                }

            }

            Spacer(Modifier.height(dimensionResource(R.dimen.large_between_header_content_padding)))

            Button(
                onClick = {
                    onDismiss()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(dimensionResource(R.dimen.small_rounded_corner)),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = stringResource(R.string.create_task),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
fun AddTaskDialogLightPreview() {
    StudyTasksTheme {
        AddTaskDialog {}
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AddTaskDialogDarkPreview() {
    StudyTasksTheme {
        AddTaskDialog {}
    }
}
