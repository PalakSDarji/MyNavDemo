package com.palak.mynavdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameLoginViewModel : ViewModel(){
    enum class AuthenticationState{
        UNAUTHENTICATED,        // Initial state, the user needs to authenticate
        AUTHENTICATED,        // The user has authenticated successfully
        INVALID_AUTHENTICATION,
        LOGGED_OUT// Authentication failed
    }

    val authenticationState = MutableLiveData<AuthenticationState>()
    var user : User?

    init{
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
        user = User()
    }

    fun refuseAuthentication(){
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

    fun authenticate(username : String, password : String){
        if(passwordIsValidForUsername(username,password)){
            this.user = User(userName = username,fullName = "full $username",bio = "bio",password = password)
            authenticationState.value = AuthenticationState.AUTHENTICATED
        }
        else {
            authenticationState.value = AuthenticationState.INVALID_AUTHENTICATION
        }
    }

    private fun passwordIsValidForUsername(username: String, password: String): Boolean {
        if(username.contentEquals("Palak") && password.contentEquals("123")) return true
        return false
    }

    fun logoutAuthentication() : Boolean{
        authenticationState.value = AuthenticationState.LOGGED_OUT
        user = null
        return true
    }

    fun authenticate(user: User) {
        authenticate(user.userName, user.password)
    }

}