package com.palak.mynavdemo.userprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import com.palak.mynavdemo.R
import com.palak.mynavdemo.databinding.UserProfileFragmentBinding

class UserProfileFragment : Fragment() {

    private var _binding : UserProfileFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = UserProfileFragment()
    }

    private val userProfileViewModel by viewModels<UserProfileViewModel>(
        factoryProducer = {SavedStateViewModelFactory(requireActivity().application,this)}
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = UserProfileFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userProfileViewModel.user.observe(viewLifecycleOwner, Observer {
            user ->
            binding.message.text = "user is $user"
            println("in observer")
            println("user is $user")
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
