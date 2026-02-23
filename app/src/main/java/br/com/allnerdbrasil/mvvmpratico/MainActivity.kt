package br.com.allnerdbrasil.mvvmpratico

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.allnerdbrasil.mvvmpratico.Repositories.MainRepository
import br.com.allnerdbrasil.mvvmpratico.ViewModel.MainViewModel
import br.com.allnerdbrasil.mvvmpratico.adapters.MainAdapter
import br.com.allnerdbrasil.mvvmpratico.databinding.ActivityMainBinding
import br.com.allnerdbrasil.mvvmpratico.model.rest.RetrofitService
import br.com.allnerdbrasil.mvvmpratico.viewmodel.Main.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val adapter = MainAdapter { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Instâncias corretas
        val service = RetrofitService.create()
        val repository = MainRepository(service)
        val factory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        binding.recyclerview.adapter = adapter

        observarViewModel()
    }

    private fun observarViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                //  Lista de lives
                launch {
                    viewModel.lives.collect { lives ->
                        Log.i("MainActivity", "Recebeu ${lives.size} lives")
                        adapter.setLiveList(lives)
                    }
                }

                //  Erro
                launch {
                    viewModel.error.collect { message ->
                        message?.let {
                            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllLives()
    }
}