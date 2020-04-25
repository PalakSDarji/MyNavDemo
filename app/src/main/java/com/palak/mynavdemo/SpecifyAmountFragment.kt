package com.palak.mynavdemo

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import java.math.BigDecimal

/**
 * A simple [Fragment] subclass.
 * Use the [SpecifyAmountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpecifyAmountFragment : Fragment(),View.OnClickListener {

    private lateinit var navController: NavController
    lateinit var recipientStr: String
    val args : SpecifyAmountFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipientStr = args.recipient
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        send_btn.setOnClickListener(this)
        cancel_btn.setOnClickListener(this)
        val message = "Sending money to $recipientStr"
        recipient.text = message
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.send_btn -> {
                if(!TextUtils.isEmpty(input_amount.text.toString())){

                    val amount = Money(BigDecimal(input_amount.text.toString()))
                    val action = SpecifyAmountFragmentDirections.actionSpecifyAmountFragmentToConfirmationFragment(recipientStr,amount)

                    //val bundle = bundleOf("recipient" to recipientStr, "amount" to amount)
                    navController.navigate(action)
                }
            }
            R.id.cancel_btn -> {
                requireActivity().onBackPressed()
            }

        }
    }
}
