package ru.churkin.health_diary.ui.page

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.churkin.health_diary.db.entity.UserEntity
import ru.churkin.health_diary.repositories.UserRepository
import ru.churkin.health_diary.ui.UserEnterState
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(PageState())

    val state = _state.asStateFlow()

    private val currentState:PageState
        get() = state.value

    init { }
}

data class PageState(
    val user: UserEntity? = null
)

