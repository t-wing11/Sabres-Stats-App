package com.example.sabresapp.ui.viewModel

import com.example.sabresapp.data.Player
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sabresapp.network.Repository
import kotlinx.coroutines.launch

class PlayerStatsViewModel(private val repository: Repository) : ViewModel() {
    private val _playerData = MutableLiveData<Player?>()
    val playerData: LiveData<Player?> get() = _playerData

    fun fetchPlayerData(playerId: String) {
        viewModelScope.launch {
            repository.getPlayerInfo(playerId, _playerData)
        }
    }



    class Factory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PlayerStatsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PlayerStatsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

