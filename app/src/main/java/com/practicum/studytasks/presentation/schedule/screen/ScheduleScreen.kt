package com.practicum.studytasks.presentation.schedule.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.studytasks.domain.model.LessonType
import com.practicum.studytasks.presentation.schedule.BreakCard
import com.practicum.studytasks.presentation.schedule.LessonCard
import com.practicum.studytasks.presentation.theme.StudyTasksTheme
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ScheduleScreen() {
    var isMonthMode by remember { mutableStateOf(false) }

    val baseDate = remember { LocalDate.of(2026, 1, 1) }
    val basePage = remember { 100000 }
    val pagerState = rememberPagerState(
        initialPage = basePage,
        pageCount = { Int.MAX_VALUE }
    )
    val scope = rememberCoroutineScope()

    val selectedDay = remember(pagerState.currentPage) {
        baseDate.plusDays((pagerState.currentPage - basePage).toLong())
    }

    LaunchedEffect(pagerState.currentPage) {
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectVerticalDragGestures { _, dragAmount ->
                    if (dragAmount < -20) isMonthMode = false
                    if (dragAmount > 20) isMonthMode = true
                }
            }
    ) {
        // calendar
        CalendarHeader(
            isMonthMode = isMonthMode,
            selectedDay = selectedDay,
            onDaySelected = { newDay ->
                val newPage = basePage + newDay.toEpochDay() - baseDate.toEpochDay()
                scope.launch {
                    pagerState.animateScrollToPage(newPage.toInt())
                }
            }
        )

        HorizontalDivider(modifier = Modifier, thickness = 0.5.dp, color = MaterialTheme.colorScheme.outline)

        // lessons in selected day
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            val currentDate = baseDate.plusDays((page - basePage).toLong())
            LessonsList(currentDate)
        }
    }
}

@Composable
internal fun CalendarHeader(
    isMonthMode: Boolean,
    selectedDay: LocalDate,
    onDaySelected: (LocalDate) -> Unit
) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(
            text = selectedDay.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale("ru")),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )
        if (isMonthMode) {
            MonthCalendar(selectedDay, onDaySelected)
        } else {
            WeekCalendar(selectedDay, onDaySelected)
        }
    }
}

@Composable
internal fun WeekCalendar(
    selectedDay: LocalDate,
    onDaySelected: (LocalDate) -> Unit
) {
    val startOfWeek = selectedDay.with(DayOfWeek.MONDAY)
    val days = (0..6).map { startOfWeek.plusDays(it.toLong()) }
    WeekDayLabels()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(bottom = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        days.forEach { day ->
            DayItem(
                date = day,
                isSelected = day == selectedDay,
                isCurrentMonth = day.month == selectedDay.month,
                onClick = { onDaySelected(day) }
            )
        }
    }
}

@Composable
internal fun MonthCalendar(
    selectedDay: LocalDate,
    onDaySelected: (LocalDate) -> Unit
) {
    val firstOfMonth = selectedDay.withDayOfMonth(1)
    val lastOfMonth = selectedDay.withDayOfMonth(selectedDay.lengthOfMonth())

    val startCalendar = firstOfMonth.with(DayOfWeek.MONDAY)
    val endCalendar = lastOfMonth.with(DayOfWeek.SUNDAY)

    val totalDays = generateSequence(startCalendar) { day ->
        if (day < endCalendar) day.plusDays(1) else null
    }.toList()

    WeekDayLabels()

    val weeks = totalDays.chunked(7)
    weeks.forEach { week ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            week.forEach { day ->
                DayItem(
                    date = day,
                    isSelected = day == selectedDay,
                    isCurrentMonth = day.month == selectedDay.month,
                    onClick = { onDaySelected(day) }
                )
            }
        }
    }
}

@Composable
internal fun DayItem(
    date: LocalDate,
    isSelected: Boolean,
    isCurrentMonth: Boolean = true,
    onClick: () -> Unit
) {
    val textColor =
        if (isCurrentMonth) MaterialTheme.colorScheme.onBackground
        else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)

    Column(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                else Color.Transparent
            )
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            fontWeight = FontWeight.Bold,
            color = textColor
        )

        Row {
            LessonType.entries
                .filter { it != LessonType.BREAK }
                .take(3)
                .forEach {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .padding(1.dp)
                            .background(it.color, CircleShape)
                    )
                }
        }
    }
}

@Composable
internal fun LessonsList(day: LocalDate) {
    val lessons = remember(day) {
        listOf(
            LessonType.LECTURE,
            LessonType.BREAK,
            LessonType.LAB,
            LessonType.BREAK,
            LessonType.PRACTICE
        )
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(lessons) { type ->
            if (type == LessonType.BREAK) {
                BreakCard()
            } else {
                LessonCard(
                    type = type,
                    title = "Вайбкодинг",
                    lessonNumber = type.ordinal + 1,
                    classroom = "Г-307-1",
                    teacher = "Неймар Джуниор"
                )
            }
        }
    }
}

@Composable
internal fun WeekDayLabels() {
    val daysOfWeek = DayOfWeek.entries.map { it.getDisplayName(TextStyle.SHORT, Locale("ru")) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        daysOfWeek.forEach { dayName ->
            Text(
                text = dayName,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.width(48.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@Composable
private fun ScheduleScreenLightPreview() {
    StudyTasksTheme {
        ScheduleScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0, locale = "ru", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ScheduleScreenDarkPreview() {
    StudyTasksTheme {
        ScheduleScreen()
    }
}