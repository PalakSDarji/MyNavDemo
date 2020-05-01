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
    var username : String

    init{
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
        username = ""
    }

    fun refuseAuthentication(){
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

    fun authenticate(username : String, password : String){
        if(passwordIsValidForUsername(username,password)){
            this.username = username
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
        username = ""
        return true
    }

}