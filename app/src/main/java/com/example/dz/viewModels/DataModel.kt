package com.example.dz.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel: ViewModel() {
    val description: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val nameItem: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val adminItem: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}