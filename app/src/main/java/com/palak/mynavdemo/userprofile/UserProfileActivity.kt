package com.palak.mynavdemo.userprofile

import android.content.Context
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.palak.mynavdemo.R

class UserProfileActivity : AppCompatActivity() {

    val userProfileViewModel by viewModels<UserProfileViewModel>()
    val userId = 2
    private lateinit var myLocationListener: MyLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UserProfileFragment.newInstance(2))
                .commitNow()
        }

        myLocationListener = MyLocationListener(this) {
            location ->
            doSomethingWithLocation(location)
        }

        lifecycle.addObserver(myLocationListener)
    }

    private fun doSomethingWithLocation(location: Location){

    }

    override fun onStart() {
        super.onStart()
        myLocationListener.start()
    }

    override fun onStop() {
        super.onStop()
        myLocationListener.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(myLocationListener)
    }

}

internal class MyLocationListener(val context: Context) : LifecycleObserver{

    constructor(context: Context, callback: (Location) -> Unit) : this(context)

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {

    }
}
