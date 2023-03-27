package ru.churkin.health_diary.ui.page

import android.annotation.SuppressLint
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import ru.churkin.health_diary.R
import ru.churkin.health_diary.ui.MainState
import ru.churkin.health_diary.ui.componentsUI.ActionButton
import ru.churkin.health_diary.ui.componentsUI.TopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PageViewScreen(
    vm: PageViewModel = hiltViewModel()
) {
    val state: PageState by vm.state.collectAsState()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
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
        Column(){

        }
    }
}