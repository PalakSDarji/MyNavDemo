package com.palak.mynavdemo.userprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.palak.mynavdemo.R

class UserProfileActivity : AppCompatActivity() {

    val userProfileViewModel by viewModels<UserProfileViewModel>()
    val userId = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UserProfileFragment.newInstance(2))
                .commitNow()
        }
    }
}
