package br.com.allnerdbrasil.mvvmpratico.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.allnerdbrasil.mvvmpratico.Repositories.MainRepository
import br.com.allnerdbrasil.mvvmpratico.model.Live
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _lives = MutableStateFlow<List<Live>>(emptyList())
    val lives: StateFlow<List<Live>> = _lives

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getAllLives() {
        viewModelScope.launch {
            repository.getAllLives()
                .catch { e ->
                    _error.value = e.message
                }
                .collect { lista ->
                    _lives.value = lista
                }
        }
    }
}