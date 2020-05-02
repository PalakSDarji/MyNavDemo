package com.palak.mynavdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentController
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_game_profile.*

/**
 * A simple [Fragment] subclass.
 */
class GameProfileFragment : Fragment() {

    private val viewModel : GameLoginViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            authenticationState ->
            when (authenticationState) {
                GameLoginViewModel.AuthenticationState.AUTHENTICATED -> showWelcomeMessage()
                GameLoginViewModel.AuthenticationState.UNAUTHENTICATED -> navController.navigate(R.id.gameLoginFragment)
                GameLoginViewModel.AuthenticationState.LOGGED_OUT -> {
                    navController.popBackStack()
                }
            }
        })

        btnLogout.setOnClickListener {
            viewModel.logoutAuthentication()
        }
    }

    private fun showWelcomeMessage() {

        txtWelcome.text = "Welcome ${viewModel.user?.userName}"
    }
}
