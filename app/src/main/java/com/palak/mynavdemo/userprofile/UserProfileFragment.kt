package com.palak.mynavdemo.userprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import com.palak.mynavdemo.databinding.UserProfileFragmentBinding
import kotlinx.android.synthetic.main.user_profile_fragment.*
import java.lang.Exception

class UserProfileFragment : Fragment() {

    private lateinit var dataBinding: UserProfileFragmentBinding

    companion object {
        fun newInstance(userID: Int): UserProfileFragment {
            val fragment = UserProfileFragment()
            val bundle = Bundle()
            bundle.putInt("userID", userID)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val userProfileViewModel by activityViewModels<UserProfileViewModel>(
        factoryProducer = {
            SavedStateViewModelFactory(
                requireActivity().application,
                this,
                requireArguments()
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dataBinding = UserProfileFragmentBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnSearch.setOnClickListener {

            try {
                val userID = etSearch.text.toString().toInt()
                userProfileViewModel.fetchUser(userID)
                    .observe(viewLifecycleOwner, Observer { user ->
                        dataBinding.user = user
                        println("user is $user")
                    })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
