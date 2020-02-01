package com.example.every.viewmodel.signup.signup.data

import androidx.lifecycle.ViewModel
import com.example.every.widget.SingleLiveEvent

class SchoolViewModel : ViewModel(){

    val onSearchEvent = SingleLiveEvent<Unit>()

    fun searchSchool() = onSearchEvent.call()
}