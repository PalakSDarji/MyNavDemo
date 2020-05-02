package com.palak.mynavdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_game_login.*

/**
 * A simple [Fragment] subclass.
 */
class GameLoginFragment : Fragment() {

    private val viewModel : GameLoginViewModel by activityViewModels()
    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        btnLogin.setOnClickListener {
            viewModel.authenticate(etUserName.text.toString(),etPassword.text.toString())
        }

        btnRegister.setOnClickListener {
            navController.navigate(R.id.action_gameLoginFragment_to_nav_game_registration)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            viewModel.refuseAuthentication()
            navController.popBackStack(R.id.gameFragment,false)
        }

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            authenticationState ->
            when(authenticationState){
                GameLoginViewModel.AuthenticationState.AUTHENTICATED -> navController.popBackStack()
                GameLoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> showErrorMessage()
            }
        })
    }

    private fun showErrorMessage() {
        Snackbar.make(requireView(),"Wrong!",Snackbar.LENGTH_LONG).show()
    }

}
