package ru.churkin.health_diary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.churkin.health_diary.ui.MainViewScreen
import ru.churkin.health_diary.ui.RootState
import ru.churkin.health_diary.ui.RootViewModel
import ru.churkin.health_diary.ui.StartScreen
import ru.churkin.health_diary.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    private val vm: RootViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state: RootState by vm.state.collectAsState()
            val navController = rememberNavController()
            AppTheme {
                if (state.splashShown) {
                    StartScreen()
                } else {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            NavigationHost(navController)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun NavigationHost(
        navController: NavHostController
    ) {
        NavHost(navController = navController, startDestination = "MainScreen") {
            composable("MainScreen") {
                MainViewScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        StartScreen()
    }
}