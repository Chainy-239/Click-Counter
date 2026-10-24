package com.viscosity.clickcounter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.setValue

class ClickCounterViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    // Brain ke andar ka secret box (Sirf ViewModel isko badal sakta hai)

    var count by mutableIntStateOf(savedStateHandle.get<Int>("current_count") ?: 0)
        private set // 👈 Iska matlab baahar se koi 'count = 50' nahi kar sakta, sirf functions se hoga

    // Logic 1: Plus karna
    fun incrementCount() {
        count++
        savedStateHandle["current_count"] = count
    }

    // Logic 2: Minus karna
    fun decrementCount() {
        if (count > 0) {
            count--
            savedStateHandle["current_count"] = count
        }
    }

    // Logic 3: Reset karna
    fun resetCount() {
        count = 0
        savedStateHandle["current_count"] = count
    }
}