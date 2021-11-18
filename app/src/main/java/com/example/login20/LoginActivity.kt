package com.example.login20

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.login20.AIfragments.AIHelloFragment
import com.example.login20.AIfragments.FragBeGone
import com.example.login20.databinding.ActivityLoginBinding
import com.example.login20.loginMVVMnetwork.AuthListener
import com.example.login20.loginMVVMnetwork.AuthViewModel

import com.example.login20.util.toast
import kotlinx.android.synthetic.main.activity_login.*

import androidx.fragment.app.Fragment
import com.example.login20.util.SharedPreference


class LoginActivity : AppCompatActivity(), AuthListener {

    private val fragmentManager = supportFragmentManager

//    private val TAG = "LoginActivity"
    companion object {
//
//        var successfulLogin: Boolean = false
//        var content_type = "application/x-www-form-urlencoded"

        //var content_type = "application/json"
//        const val CLIENT_ID = R.string.miinibio
//        const val CLIENT_SECRET = R.string.miinisecret
//        const val USERNAME = "USERNAME"

//        var authString = "$CLIENT_ID:$CLIENT_SECRET"
//        var encodedAuthString: String =
//            Base64.encodeToString(authString.toByteArray(), Base64.NO_WRAP)
//        var auth = "Basic $encodedAuthString"


        var username = ""
        lateinit var password: String
        //  var admins : Boolean = false
        //  var userid: Long = 12314546
        //  var ulatitude: Double = 0.0
        //  var ulongitude: Double = 0.0
        //  var username4D: String = ""

    }

//    override var validatedUsername: Boolean = false
//    override var validatedPassword: Boolean = false
//    override var error: Boolean? = false

    var binding: ActivityLoginBinding? = null
    var viewmodel: AuthViewModel? = null

    //var customeProgressDialog: CustomeProgressDialog? = null

    // trying to remember the user with shared prefs:
    var sp: SharedPreferences? = null
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewmodel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding?.viewModel = viewmodel
        viewmodel!!.authListener = this
        ///customeProgressDialog = CustomeProgressDialog(this)
        initObservables()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.add(R.id.constraint_layout_login, AIHelloFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Toast.makeText(this@LoginActivity, "ticking", Toast.LENGTH_SHORT).show()
            }
            override fun onFinish() {

                val newFragment: Fragment = FragBeGone()
                val transaction = supportFragmentManager.beginTransaction()

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                transaction.replace(R.id.constraint_layout_login, newFragment)
                transaction.addToBackStack(null)

// Commit the transaction

// Commit the transaction
                transaction.commit()
            }
        }.start()
        //



        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        progress_login.visibility = View.INVISIBLE










    }

    override fun onStarted() {

        toast("logging in...")
        val sharedPreference:SharedPreference= SharedPreference(this)
        val name = usernamePlease.text.toString()
        sharedPreference.save("username", name)
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            val sharedPreference:SharedPreference= SharedPreference(this)
            val welcomeUser = sharedPreference.getValueString("username")
            toast("Welcome $welcomeUser")
            // sending user to tab home activity


            sp?.edit()?.putBoolean("logged",true)?.apply();
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()


        })

    }

    override fun onFailure(message: String) {
        toast(message)
    }



    private fun initObservables() {
        viewmodel?.progressDialog?.observe(this, Observer {
            if (it!!) {
                //customeProgressDialog?.show()
            } else {
                //customeProgressDialog?.dismiss()
            }
        })

        viewmodel?.userLogin?.observe(this, Observer {
            val sharedPreference:SharedPreference= SharedPreference(this)
            val welcomeUser = sharedPreference.getValueString("username")
            toast("Welcome $welcomeUser")

        })
    }



}
