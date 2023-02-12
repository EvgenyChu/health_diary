package ru.churkin.health_diary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.churkin.health_diary.modelData.Diary
import ru.churkin.health_diary.db.entity.UserEntity

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()
    val currentState = _state.value

    init {
        viewModelScope.launch {
            delay(2000)
            _state.update {
                it.copy(screen = MainScreen.EmptyList)
            }
        }
    }
}

data class MainState(
    val user: UserEntity? = null,
    val screen: MainScreen = MainScreen.Loading
)

sealed class MainScreen {
    object Loading : MainScreen()
    object EmptyList: MainScreen()
    data class MainView(
        val listDiary: List<Diary> = emptyList()
    ) : MainScreen()
}