package com.example.otpactiviy.utils

import android.app.ProgressDialog
import android.content.Context
import com.example.otpactiviy.R

class ProgressHelper {
    var context: Context? = null
    private var progressDialog: ProgressDialog? = null
    fun showProgress(context: Context) {
        try {
            if (progressDialog != null && progressDialog!!.isShowing) {
                return
            }
            progressDialog = ProgressDialog(context)
            progressDialog!!.setMessage(
                "Please Wait"
            )
            progressDialog!!.setProgressStyle(R.style.AppTheme)
            progressDialog!!.setCancelable(false)
            progressDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun dismissProgress() {
        try {
            if (null != progressDialog && progressDialog!!.isShowing) progressDialog!!.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        fun newInstance() = ProgressHelper()
    }
}