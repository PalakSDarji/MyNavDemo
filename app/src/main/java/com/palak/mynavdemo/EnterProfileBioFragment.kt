package com.palak.mynavdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_enter_profile_bio.*

/**
 * A simple [Fragment] subclass.
 */
class EnterProfileBioFragment : Fragment() {

    private val loginViewModel by activityViewModels<GameLoginViewModel>()
    private lateinit var navController: NavController

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
        return inflater.inflate(R.layout.fragment_enter_profile_bio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        btnNext.setOnClickListener {
            val user = User(fullName = etFullName.text.toString(), bio = etBio.text.toString())
            val action = EnterProfileBioFragmentDirections.actionEnterProfileBioFragmentToRegisterFragment(user)
            navController.navigate(action)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,backPress)
    }
}
