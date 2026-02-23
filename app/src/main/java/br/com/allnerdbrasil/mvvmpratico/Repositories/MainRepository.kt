package br.com.allnerdbrasil.mvvmpratico.Repositories

import br.com.allnerdbrasil.mvvmpratico.model.Live
import br.com.allnerdbrasil.mvvmpratico.model.rest.RetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository(
    private val retrofitService: RetrofitService
) {

    fun getAllLives(): Flow<List<Live>> = flow {
        emit(retrofitService.getAllLives())
    }
}