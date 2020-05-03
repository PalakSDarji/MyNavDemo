package com.palak.mynavdemo.userprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.palak.mynavdemo.User

class UserProfileViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val userID : String = savedStateHandle["uid"] ?: throw java.lang.IllegalArgumentException("missing user id")
    lateinit var user : LiveData<User>

    init {
        println("UserProfileViewModel init block.")
    }

    override fun onCleared() {
        super.onCleared()
        println("UserProfileViewModel cleared")
    }
}
