package ru.churkin.health_diary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RootViewModel : ViewModel(){
    private val _state = MutableStateFlow(RootState())
    val state = _state.asStateFlow()
    val currentState = _state.value

    init {
        viewModelScope.launch {
            delay(2000)
            _state.update { it.copy(splashShown = false) }
        }
    }
}

data class RootState(
    val splashShown: Boolean = true
)