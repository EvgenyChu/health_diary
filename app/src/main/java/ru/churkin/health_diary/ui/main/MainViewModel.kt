package ru.churkin.health_diary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.churkin.health_diary.modelData.Diary
import ru.churkin.health_diary.db.entity.UserEntity
import ru.churkin.health_diary.repositories.UserRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()
    private val currentState: MainState
        get() = state.value

    init {
        viewModelScope.launch {
            val user = repository.getActiveUser()
            _state.value = currentState.copy(
                user = user,
                screen = MainScreen.EmptyList
            )
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