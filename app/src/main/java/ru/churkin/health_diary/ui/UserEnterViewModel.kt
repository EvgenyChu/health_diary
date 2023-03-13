package ru.churkin.health_diary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.churkin.health_diary.db.entity.UserEntity
import ru.churkin.health_diary.modelData.Diary

class UserEnterViewModel : ViewModel() {
    private val _state = MutableStateFlow(UserEnterState())
    val state = _state.asStateFlow()
    val currentState = _state.value

    init {
        viewModelScope.launch {
            val user = UserEntity(-1, "Evgeny", 34, 82)
            _state.update {
                it.copy(screen = UserEnterScreen.UserEnterView(user))
            }
        }
    }
}

data class UserEnterState(
    val user: UserEntity? = null,
    val screen: UserEnterScreen = UserEnterScreen.Loading
)

sealed class UserEnterScreen {
    object Loading : UserEnterScreen()
    data class UserEnterView(
        val user: UserEntity? = null
    ) : UserEnterScreen()
}