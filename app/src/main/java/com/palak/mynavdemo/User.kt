package com.palak.mynavdemo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(var userID : Int = 2, var userName : String = "", var fullName : String = "", var bio : String = "", var password : String = "") : Parcelable