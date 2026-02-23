package br.com.allnerdbrasil.mvvmpratico.viewmodel.Main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.allnerdbrasil.mvvmpratico.Repositories.MainRepository
import br.com.allnerdbrasil.mvvmpratico.ViewModel.MainViewModel

class MainViewModelFactory constructor(private val repository: MainRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}