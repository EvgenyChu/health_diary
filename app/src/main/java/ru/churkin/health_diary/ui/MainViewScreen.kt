package ru.churkin.health_diary.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.churkin.health_diary.R
import ru.churkin.health_diary.ui.componentsUI.ActionButton
import ru.churkin.health_diary.ui.componentsUI.DiaryCard
import ru.churkin.health_diary.ui.componentsUI.Loading

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainViewScreen(
    vm: MainViewModel = viewModel()
) {
    val state: MainState by vm.state.collectAsState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        floatingActionButton = {
            ActionButton() {}
        }
    ) {
        when (val screen = state.screen) {
            is MainScreen.Loading -> Loading()
            is MainScreen.EmptyList -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.text_empty_content),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            is MainScreen.MainView -> {
                LazyColumn() {
                    items(items = screen.listDiary, { it.id }) { diary ->
                        DiaryCard(diary = diary)
                    }
                }
            }
        }
    }
}