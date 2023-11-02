package com.example.otpactiviy.presentation.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.otpactiviy.data.ApiModel
import com.example.otpactiviy.utils.RetrofitInstanceClass
import com.example.otpactiviy.utils.httpResultInterface
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback


class MainViewModel : ViewModel() {

    lateinit var mobileNumber : String

    fun generateOtp(mobNumber: String, httpResultInterface: httpResultInterface,context : Context) {
        /*val requestQueue = Volley.newRequestQueue(context)

        val stringReq = object :StringRequest(
            Method.POST,
            "https://tansh.com/api/customer-login/generate_login_otp",
            Response.Listener{
                Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
                httpResultInterface.onSuccess(it.toString())
            },
            Response.ErrorListener{
                Toast.makeText(context,"error",Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                var params = HashMap<String,String>()
                params.put("mno",mobNumber)
                Log.d("mobile",params.toString())
                return params
            }

            override fun getHeaders(): MutableMap<String, String> {
                var params = HashMap<String,String>()
                params.put("api-key","97c7038e2ab7ba61cc27f400c1b52eb17dde25f2a8ce76f778")
                return params
            }
        }

        requestQueue.add(stringReq)
*/
        mobileNumber = mobNumber
        val call = RetrofitInstanceClass.getApiCall()?.generateOTP(mobNumber)
        call?.enqueue(object : Callback<ApiModel> {

            override fun onFailure(call: Call<ApiModel?>, t: Throwable) {
                httpResultInterface.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<ApiModel>, response: retrofit2.Response<ApiModel>) {
                if(response.code() == 200 || response.code() == 201){
                    httpResultInterface.onSuccess(response.body()!!.message)
                }else if(response.code() == 400){
                    httpResultInterface.onFailure(response.errorBody()!!.string())
                }
            }
        })
    }

    fun verifyOtp(mno : String, otp : String, httpResultInterface: httpResultInterface){
        val call = RetrofitInstanceClass.getApiCall().verifyOTP(mno,otp)

        call.enqueue(object : Callback<ApiModel?> {
            override fun onResponse(
                call: Call<ApiModel?>,
                response: retrofit2.Response<ApiModel?>,
            ) {
                if(response.code() == 200 || response.code() == 201){
                    httpResultInterface.onSuccess(response.message())
                }else if(response.code() == 400){
                    httpResultInterface.onFailure(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ApiModel?>, t: Throwable) {
                httpResultInterface.onFailure(t.message.toString())
            }
        })
    }
}
