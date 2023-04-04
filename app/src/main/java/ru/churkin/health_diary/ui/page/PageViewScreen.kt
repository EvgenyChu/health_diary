package ru.churkin.health_diary.ui.page

import android.annotation.SuppressLint
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.churkin.health_diary.R
import ru.churkin.health_diary.helpers.formatDateForUser
import ru.churkin.health_diary.helpers.formatDateWithoutTime
import ru.churkin.health_diary.ui.MainState
import ru.churkin.health_diary.ui.componentsUI.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PageViewScreen(
    vm: PageViewModel = hiltViewModel()
) {
    val state: PageState by vm.state.collectAsState()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
    var isShowDatePicker by remember { mutableStateOf(false) }
    var isShowTimePicker by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopBar(title = state.user?.name ?: "") { backDispatcher.onBackPressed() }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(
                    id = R.drawable.health_background
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            DataField(
                text = state.date.formatDateWithoutTime(),
                label = R.string.date_measuring,
                iconTint = MaterialTheme.colors.onPrimary,
                icon = R.drawable.ic_baseline_calendar_month_24
            ) {
                isShowDatePicker = true
            }
            DataField(
                text = state.time,
                label = R.string.time_measuring,
                iconTint = MaterialTheme.colors.onPrimary,
                icon = R.drawable.ic_baseline_access_time_24
            ) {
                isShowTimePicker = true
            }
            Spacer(modifier = Modifier.height(8.dp))
            InfoField(
                text = state.sys?.toString() ?: "",
                label = R.string.SYS_full_text,
                keyboardType = KeyboardType.Number,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }),
                imeAction = ImeAction.Done,
                onValueChange = { vm.updateSys(it.take(3)) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            InfoField(
                text = state.dia?.toString() ?: "",
                label = R.string.DIA_full_text,
                keyboardType = KeyboardType.Number,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }),
                imeAction = ImeAction.Done,
                onValueChange = { vm.updateDia(it.take(3)) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            InfoField(
                text = state.pul?.toString() ?: "",
                label = R.string.PUL_full_text,
                keyboardType = KeyboardType.Number,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }),
                imeAction = ImeAction.Done,
                onValueChange = { vm.updatePul(it.take(3)) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            InfoField(
                text = state.note,
                label = R.string.note_necessary,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }),
                imeAction = ImeAction.Done,
                onValueChange = { vm.updateNote(it.take(800)) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            EnterButton(text = R.string.button_add) {

            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (isShowDatePicker) DatePicker(
            onSelect = {
                vm.updateDate(it)
                isShowDatePicker = false
            },
            onDismiss = { isShowDatePicker = false }
        )

        if (isShowTimePicker) TimePicker(
            onSelect = {
                vm.updateTime(it)
                isShowTimePicker = false
            },
            onDismiss = { isShowTimePicker = false }
        )
    }
}