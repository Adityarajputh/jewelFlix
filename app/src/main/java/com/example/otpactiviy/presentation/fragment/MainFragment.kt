package com.example.otpactiviy.presentation.fragment

import android.app.ProgressDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.example.otpactiviy.R
import com.example.otpactiviy.data.ApiModel
import com.example.otpactiviy.databinding.FragmentMainBinding
import com.example.otpactiviy.presentation.viewModel.MainViewModel
import com.example.otpactiviy.utils.ProgressHelper
import com.example.otpactiviy.utils.httpResultInterface
import com.google.gson.Gson

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mainBinding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return mainBinding.root
    }

    override fun onResume() {
        super.onResume()
        val progressHelper = ProgressHelper.newInstance()
        mainBinding.doneBtn.setOnClickListener {
            if (mainBinding.mobileNumEt.text.toString().isNotEmpty()) {
                progressHelper.showProgress(requireContext())
                viewModel.mobileNumber = mainBinding.mobileNumEt.text.toString()
                viewModel.generateOtp(
                    mainBinding.mobileNumEt.text.toString(),
                    object : httpResultInterface {
                        override fun onSuccess(response: String) {
                            Toast.makeText(
                                requireContext(),
                                response,
                                Toast.LENGTH_SHORT
                            ).show()
                            /*requireActivity().supportFragmentManager.beginTransaction()
                                .replace(R.id.container, OtpFragment.newInstance())
                                .addToBackStack("otp")
                                .commit()*/
                            progressHelper.dismissProgress()
                            val modalBottomSheet = OtpFragment()
                            modalBottomSheet.show(requireActivity().supportFragmentManager, OtpFragment.TAG)
                        }

                        override fun onFailure(exception: String) {
                            val apiResponse = Gson().fromJson(exception,ApiModel::class.java)
                            Toast.makeText(
                                requireContext(),
                                apiResponse.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            progressHelper.dismissProgress()
                            val modalBottomSheet = OtpFragment()
                            modalBottomSheet.show(requireActivity().supportFragmentManager, OtpFragment.TAG)
                        }
                    },
                    requireContext()
                )
            } else {
                Toast.makeText(requireContext(), "Please Enter Mobile Number", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

}