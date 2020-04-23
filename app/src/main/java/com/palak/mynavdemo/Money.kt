package com.palak.mynavdemo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Money(var money : BigDecimal) : Parcelable {

}