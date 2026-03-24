package com.example.lablearnandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SensorViewModel(application: Application) : AndroidViewModel(application) {
    // กำหนดตัวแปรสถานะเป็น MutableStateFlow
    private val _sensorData = MutableStateFlow("รอพิกัด...")
    val sensorData: StateFlow<String> = _sensorData.asStateFlow()

    private val sensorTracker = SensorTracker(application)

    fun startTracking() {
        sensorTracker.startListening { newValue ->
            _sensorData.value = newValue
        }
    }

    override fun onCleared() {
        super.onCleared()
        sensorTracker.stopListening()
    }
}
