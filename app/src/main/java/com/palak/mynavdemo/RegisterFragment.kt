package com.palak.mynavdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_register.*
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private lateinit var user : User
    private lateinit var navController: NavController
    private val loginViewModel by activityViewModels<GameLoginViewModel>()

    private var backPress = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            loginViewModel.refuseAuthentication()
            navController.popBackStack(R.id.gameLoginFragment, false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = requireArguments().getParcelable("user")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        btnRegisterAndLogin.setOnClickListener {
            user.userName = etUserName.text.toString()
            user.password = etPassword.text.toString()

            loginViewModel.authenticate(user)
        }

        loginViewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            authenticateState ->
            when(authenticateState){
                GameLoginViewModel.AuthenticationState.AUTHENTICATED -> navController.popBackStack(R.id.gameProfileFragment, false)
            }
        })

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,backPress)
    }

}
