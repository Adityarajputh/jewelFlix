package com.example.otpactiviy.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.otpactiviy.R
import com.example.otpactiviy.data.ApiModel
import com.example.otpactiviy.databinding.FragmentMainBinding
import com.example.otpactiviy.databinding.FragmentOtpBinding
import com.example.otpactiviy.presentation.viewModel.MainViewModel
import com.example.otpactiviy.utils.httpResultInterface
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson


class OtpFragment : BottomSheetDialogFragment() {

    private lateinit var bottomSheetBinding: FragmentOtpBinding
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        bottomSheetBinding = FragmentOtpBinding.inflate(inflater,container,false)
        return bottomSheetBinding.root
    }

    override fun onResume() {
        super.onResume()
        bottomSheetBinding.bottomSheetBtn.setOnClickListener {
            if (bottomSheetBinding.otpEt.text.toString().isNotEmpty()) {
                mainViewModel.verifyOtp(
                    mainViewModel.mobileNumber,
                    bottomSheetBinding.otpEt.text.toString(),
                    object : httpResultInterface {
                        override fun onSuccess(response: String) {
                            val apiModel = Gson().fromJson(response,ApiModel::class.java)
                            Toast.makeText(
                                requireContext(),
                                apiModel.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            dismiss()
                        }

                        override fun onFailure(exception: String) {
                            val apiModel = Gson().fromJson(exception,ApiModel::class.java)
                            Toast.makeText(
                                requireContext(),
                                apiModel.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            dismiss()
                        }
                    })
            }
        }
    }

    companion object {
        fun newInstance() = OtpFragment()
        val TAG = "OTPFragment"
    }
}