package com.palak.mynavdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_third_nav.*

/**
 * A simple [Fragment] subclass.
 */
class ThirdNavFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_nav, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonToGo.setOnClickListener(this)
        navController = Navigation.findNavController(view)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.buttonToGo -> {
                navController.navigate(ThirdNavFragmentDirections.actionThirdNavFragmentToMainFragment())
            }
        }
    }
}
