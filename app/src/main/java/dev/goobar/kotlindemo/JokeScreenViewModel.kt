package dev.goobar.kotlindemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeScreenViewModel @Inject constructor(private val service: JokeService) : ViewModel() {

  private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
  val state = _state.asStateFlow()

  init {
    refresh()
  }

  fun refresh() {
    TODO("Implement joke refresh")
  }

  data class UiState(
    val isLoading: Boolean = true,
    val setup: String = "",
    val punchline: String = ""
  )

}