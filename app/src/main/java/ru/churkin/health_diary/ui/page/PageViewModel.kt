package ru.churkin.health_diary.ui.page

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.churkin.health_diary.db.entity.UserEntity
import ru.churkin.health_diary.helpers.toDate
import ru.churkin.health_diary.repositories.UserRepository
import ru.churkin.health_diary.ui.MainScreen
import ru.churkin.health_diary.ui.UserEnterState
import java.util.*
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

    fun updateDate(date: String) {
        _state.value = currentState.copy(date = date.toDate())
    }

    fun updateTime(time: String) {
        _state.value = currentState.copy(time = time)
    }

    fun updateSys(sys: String) {
        val correctSys = sys.filter { it.isDigit() }
        _state.value = currentState.copy(sys = correctSys.toIntOrNull())
    }

    fun updateDia(dia: String) {
        val correctDia = dia.filter { it.isDigit() }
        _state.value = currentState.copy(dia = correctDia.toIntOrNull())
    }

    fun updatePul(pul: String) {
        val correctPul = pul.filter { it.isDigit() }
        _state.value = currentState.copy(pul = correctPul.toIntOrNull())
    }

    fun updateNote(note: String) {
        _state.value = currentState.copy(note = note)
    }
}

data class PageState(
    val user: UserEntity? = null,
    val date: Date = Date(),
    val time: String = "",
    val sys: Int? = null,
    val dia: Int? = null,
    val pul: Int? = null,
    val note: String = ""
)

