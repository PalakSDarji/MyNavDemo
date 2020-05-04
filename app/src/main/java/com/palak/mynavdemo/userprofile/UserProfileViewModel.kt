package com.palak.mynavdemo.userprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.palak.mynavdemo.User
import kotlinx.android.synthetic.main.fragment_enter_profile_bio.*

class UserProfileViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val userID : Int = savedStateHandle["userID"] ?: throw java.lang.IllegalArgumentException("missing user id")
    lateinit var user : MutableLiveData<User>

    init {
        println("UserProfileViewModel init block. " + userID)
    }

    fun fetchUser(userID : Int) : MutableLiveData<User>{
        user = MutableLiveData()
        if(userID == 1){
            user.value = User(userName =  "PalakSDarji", fullName = "Palak", bio = "Palak's Bio")
        }
        else if(userID == 2){
            user.value = User(userName =  "dharadarji", fullName = "Dhara", bio = "Dhara's Bio")
        }
        else if(userID == 3){
            user.value = User(userName =  "ajayvyas", fullName = "Ajay", bio = "Ajay's Bio")
        }
        return user
    }

    override fun onCleared() {
        super.onCleared()
        println("UserProfileViewModel cleared")
    }
}
