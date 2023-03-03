package com.example.assignment01code00nnn.ui.addcontact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddContactViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is addcontact Fragment"
    }
    val text: LiveData<String> = _text
}