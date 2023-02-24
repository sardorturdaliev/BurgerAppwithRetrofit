package com.sardordev.burgerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardordev.burgerapp.domain.BurgerRepository
import com.sardordev.burgerapp.utils.ResourceEvent
import com.sardordev.burgerapp.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BurgerViewModel @Inject constructor(private val burgerRepository: BurgerRepository) :
    ViewModel() {
    private val _burgerListObserver = MutableStateFlow<UiEvent>(UiEvent.Empty)
    val burgerListObserver: StateFlow<UiEvent> get() = _burgerListObserver

    fun getBurgersList() {
        viewModelScope.launch {
            _burgerListObserver.value = UiEvent.Loading
            val result = burgerRepository.getBurgerList()
            when (result) {
                is ResourceEvent.Error -> {
                    _burgerListObserver.value = UiEvent.Error(message = result.message)
                }
                is ResourceEvent.Success -> {
                    _burgerListObserver.value = UiEvent.Success(result.data)
                }
            }
        }
    }

}