package com.palak.mynavdemo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_confirmation.*

/**
 * A simple [Fragment] subclass.
 */
class ConfirmationFragment : Fragment() {

    lateinit var recipientStr: String
    lateinit var amount: Money
    lateinit var navController: NavController

    var backPressedCallBack = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            redirectAction()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipientStr = requireArguments().getString("recipient").toString()
        amount = requireArguments().getParcelable("amount")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallBack)
        navController = Navigation.findNavController(view)

        confirmation_message.text = "You have sent ${amount.money} to $recipientStr"
    }

    private fun redirectAction() {
        if (recipientStr.contentEquals("Palak")) {
            backPressedCallBack.isEnabled = false
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallBack)
            requireActivity().onBackPressed()
        } else {
            navController.navigate(R.id.action_confirmationFragment_to_mainFragment)
        }
    }
}
