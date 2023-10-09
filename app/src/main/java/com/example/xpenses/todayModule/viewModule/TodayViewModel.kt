package com.example.xpenses.todayModule.viewModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xpenses.common.entities.WorkDay
import com.example.xpenses.todayModule.module.TodayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(private val repository: TodayRepository) : ViewModel() {

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val _isHideKeyboard = MutableLiveData<Boolean>()
    val isHideKeyboard: LiveData<Boolean> = _isHideKeyboard

    private val _snackbarMsg = MutableLiveData<Int>()
    val snackbarMsg: LiveData<Int> = _snackbarMsg

    private val _currentWorkDay = MutableLiveData<WorkDay>()
    val currentWorkDay: LiveData<WorkDay> = _currentWorkDay

    fun getWorkDay() {
        _inProgress.value = true
        _isHideKeyboard.value = true
        repository.getWorkDayByDay { result ->
            result.let {
                _currentWorkDay.value = it
                _inProgress.value = false
                _isHideKeyboard.value = false
            }
        }
    }

    fun pause() {
        repository.pause()
    }

    fun saveWorkDay(startCapital: Double?, finalCapital: Double?, expense: Double?) {
        _inProgress.value = true
        _isHideKeyboard.value = true
        val workDay = _currentWorkDay.value ?: WorkDay()

        repository.saveWorkDay(startCapital,finalCapital,expense, workDay){resMsg ->
            resMsg.let { _snackbarMsg.value = it }
            _inProgress.value = false
        }
    }

}