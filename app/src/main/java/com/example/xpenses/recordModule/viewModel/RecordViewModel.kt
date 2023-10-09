package com.example.xpenses.recordModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xpenses.common.entities.WorkDay
import com.example.xpenses.recordModule.module.RecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(private val repository: RecordRepository) : ViewModel() {

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private val _workDays = MutableLiveData<List<WorkDay>>()
    val workDays: LiveData<List<WorkDay>> = _workDays

    fun getAllWorkDay() {
        _inProgress.value = true
        repository.getAllWorkDay { result ->
            result?.let {
                _workDays.value = it
                _inProgress.value = false
            }
            _isError.value = result == null
            _inProgress.value = false
        }
    }
}