package com.santhosh.assignment.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santhosh.assignment.utils.isNotEmpty

class AgeCalculatorViewModel : ViewModel() {
    val firstName = MutableLiveData("")
    val lastName = MutableLiveData("")
    val dob = MutableLiveData("")
    val enableNext = MediatorLiveData<Boolean>().apply {
        addSource(firstName) {
            value = validateFields()
        }
        addSource(lastName) {
            value = validateFields()
        }
        addSource(dob) {
            value = validateFields()
        }
    }

    private fun validateFields(): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty() && dob.isNotEmpty()
    }
}