package com.example.otpactiviy.utils

import java.lang.Exception

interface httpResultInterface {
    fun onSuccess(response : String)
    fun onFailure(exception: String)
}