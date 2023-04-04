package ru.churkin.health_diary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.churkin.health_diary.App
import ru.churkin.health_diary.repositories.UserRepository
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(RootState())
    val state = _state.asStateFlow()
    val currentState = _state.value

    init {
        viewModelScope.launch {
            val hasUser = repository.getAllUsers().isNotEmpty()
            delay(1800)
            _state.update { it.copy(splashShown = false, hasUser = hasUser) }
            repository.saveVersionAppAfterUpdate()
        }
    }
}

data class RootState(
    val splashShown: Boolean = true,
    val hasUser: Boolean = false
)