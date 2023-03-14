package ru.churkin.health_diary.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.churkin.health_diary.db.entity.UserEntity
import ru.churkin.health_diary.modelData.Diary

class UserEnterViewModel : ViewModel() {
    private val _state = MutableStateFlow(UserEnterState())
    val state = _state
    private val currentState = state.value

    init {
        Log.e("UserEnterViewModel", "init")
            initScreen()
    }
    private fun initScreen(){
        _state.value = currentState.copy(screen = UserEnterScreen.UserEnterView())
    }

    fun updateName(name: String) {
        Log.e("UserEnterViewModel", "$name ${currentState.screen}")
        if (currentState.screen !is UserEnterScreen.UserEnterView) return
        val user = currentState.screen
        _state.value = currentState.copy(screen = user.copy(name = name))
        }
}

data class UserEnterState(
    val screen: UserEnterScreen = UserEnterScreen.Loading
)

sealed class UserEnterScreen {
    object Loading : UserEnterScreen()
    data class UserEnterView(
        val name: String = "",
        val age: String = "",
        val weight: String = ""
    ) : UserEnterScreen()
}