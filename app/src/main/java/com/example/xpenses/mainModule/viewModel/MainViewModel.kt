package com.example.xpenses.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.xpenses.R
import com.example.xpenses.common.entities.FirebaseUser
import com.example.xpenses.mainModule.model.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val initialSetupEvent = liveData {
        emit(repository.fetchInitialPreferences())
    }

    private val _snackbarMsg = MutableLiveData<Int>()
    val snackbarMsg: LiveData<Int> = _snackbarMsg

    private val _user = MutableLiveData<FirebaseUser>()
    val user: LiveData<FirebaseUser> = _user

    init {
        getUser()
    }

    private fun getUser() {
        repository.getUser { result ->
            _user.value = result
            if (result != null) _snackbarMsg.value = R.string.main_auth_success

        }
    }

    fun resume() {
        repository.resume()
    }

    fun pause() {
        repository.pause()
    }

    fun updateLastDestination(lastDestination:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLastDestination(lastDestination)
        }
    }

}