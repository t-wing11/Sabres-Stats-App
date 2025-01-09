package com.example.sabresapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sabresapp.data.Team
import com.example.sabresapp.network.Repository
import kotlinx.coroutines.launch

class RosterViewModel(private val repository: Repository) : ViewModel() {
    private val _rosterData = MutableLiveData<Team?>()
    val rosterData: LiveData<Team?> get() = _rosterData

    fun fetchRoster(season: String) {
        viewModelScope.launch {
            repository.getRoster(season, _rosterData)
        }
    }

    class Factory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RosterViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RosterViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

