package com.example.every.viewmodel.signup.signup.data

import androidx.lifecycle.ViewModel
import com.example.every.widget.SingleLiveEvent

class SchoolViewModel : ViewModel(){

    val onSearchechEvent = SingleLiveEvent<Unit>()

    fun searchSchool() = onSearchechEvent.call()
}