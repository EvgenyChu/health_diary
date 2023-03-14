package ru.churkin.health_diary.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.churkin.health_diary.db.entity.UserEntity
import ru.churkin.health_diary.modelData.Diary
import ru.churkin.health_diary.repositories.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserEnterViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(UserEnterState())

    val state = _state.asStateFlow()

    private val currentState: UserEnterState
    get() = state.value

    init {
        Log.e("UserEnterViewModel", "init")
            initScreen()
    }
    private fun initScreen(){
        _state.value = currentState.copy(screen = UserEnterScreen.UserEnterView())
    }

    private fun isEnterView() :  UserEnterScreen.UserEnterView? {
        return if (currentState.screen !is UserEnterScreen.UserEnterView) null
        else currentState.screen as UserEnterScreen.UserEnterView
    }

    fun updateName(name: String) {
        val user = isEnterView() ?: return
        _state.value = currentState.copy(screen = user.copy(name = name))
        }

    fun updateAge(age: String) {
        val user = isEnterView() ?: return
        _state.value = currentState.copy(screen = user.copy(age = age))
    }

    fun updateWeight(weight: String) {
        val user = isEnterView() ?: return
        _state.value = currentState.copy(screen = user.copy(weight = weight))
    }

    fun saveUser() {
        val screen = isEnterView() ?: return
        val user = UserEntity(
            userId = -1,
            name = screen.name,
            age = screen.age.toIntOrNull() ?: 0,
            weight = screen.weight.toIntOrNull() ?: 0,
            isActive = true
        )
        viewModelScope.launch { repository.addUser(user) }
    }

    fun clearScreen() {
        isEnterView() ?: return
        _state.value = currentState.copy(screen = UserEnterScreen.UserEnterView())
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