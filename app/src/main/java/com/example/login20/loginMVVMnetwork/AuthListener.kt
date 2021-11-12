package com.stepashka.buildinglocator2.loginMVVMnetwork

import androidx.lifecycle.LiveData
import com.example.login20.AIfragments.AIHelloFragment

interface AuthListener {

    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message : String)

}