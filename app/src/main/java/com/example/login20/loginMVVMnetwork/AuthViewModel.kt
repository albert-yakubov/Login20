package com.example.login20.loginMVVMnetwork

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.login20.models.UserObservable
import com.example.login20.util.SingleLiveEvent
import com.example.login20.util.Util
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.util.Base64
import com.example.login20.util.SharedPreference
import com.stepashka.buildinglocator2.loginMVVMnetwork.UserRepository


class AuthViewModel(application: Application) : AndroidViewModel(application){


    private var context: Context? = null

    var authListener : AuthListener? = null
    var sp: SharedPreferences? = null

    fun LoginViewModel(context: Context?) {
        this.context = context
    }

    companion object{

        var successfulLogin: Boolean = false
        var content_type = "application/x-www-form-urlencoded"
        //var content_type = "application/json"
        private const val CLIENT_ID = "lambdaclient"

        private const val CLIENT_SECRET = "lambdasecret"


        var authString = "$CLIENT_ID:$CLIENT_SECRET"
        var encodedAuthString: String = Base64.encodeToString(authString.toByteArray(), Base64.NO_WRAP)
        var auth = "Basic $encodedAuthString"


//        var username = ""
//        lateinit var password: String
        //  var admins : Boolean = false
        //  var userid: Long = 12314546
        //  var ulatitude: Double = 0.0
        //  var ulongitude: Double = 0.0
        //  var username4D: String = ""

    }

    var btnSelected: ObservableBoolean? = null
    var username: String? = null
    var password: String? = null
    var progressDialog: SingleLiveEvent<Boolean>? = null

    var userLogin: MutableLiveData<UserObservable>? = null

    init {
        btnSelected = ObservableBoolean(false)
        progressDialog = SingleLiveEvent<Boolean>()
        username = ""
        password = ""
        userLogin = MutableLiveData<UserObservable>()
    }

    fun onEmailChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(s.toString()))


    }

    fun onPasswordChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(s.toString()))


    }



    fun login(){

        progressDialog?.value = true
        authListener?.onStarted()
        sp?.edit()?.putString("username", username.toString())?.apply()

        val call: Call<ResponseBody> = ServiceBuilder.create()
            .login( auth, content_type, username.toString(), password.toString() )


        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                progressDialog?.value = false

                login()

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {


                successfulLogin = response.isSuccessful
                progressDialog?.value = false

                if (successfulLogin) {
                    val loginResponse = UserRepository().userLoginMVVM(
                        auth,
                        content_type,
                        username.toString(),
                        password.toString()
                    )

                    successfulLogin = false
                    authListener?.onSuccess(loginResponse)


                } else {
                    Toast.makeText(
                        this@AuthViewModel.getApplication(),
                        "Please check username and password!",
                        Toast.LENGTH_LONG).show()
                }
            }

        })
    }

}