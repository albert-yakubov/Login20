package com.example.login20

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login20.loginMVVMnetwork.ServiceBuilder
import com.example.login20.util.SharedPreference
import com.example.login20.models.User
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var username = ""
    var userid =1L
    var minibio = ""
    var location =""
    val sp: SharedPreferences? = null


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            val sharedPreference: SharedPreference = SharedPreference(this)
            usernameText.text = sharedPreference.getValueString("username")

        //getUser()


    }

//    fun getUser(){
//
//
//        val call: Call<User> = ServiceBuilder.create().getUser3(ui.toString())
//        call.enqueue(object: Callback<User> {
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "Connection Timed Out! Try Again!", Toast.LENGTH_LONG).show()
//            }
//
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                if(response.isSuccessful) {
//
//
//
//
//                    username = response.body()?.username ?: ""
//                   userid =    response.body()?.userid ?: 1231234
//                    minibio =  response.body()?.mini_bio ?: ""
//
//                    location = response.body()?.location ?: ""
//                    //usernameText.text = sp?.getString("un", null)
//
//                    Toast.makeText(this@MainActivity, "Connected!", Toast.LENGTH_SHORT).show()
//
//
//                }
//                else{
//                    Toast.makeText(this@MainActivity, "Invalid Login info!", Toast.LENGTH_LONG).show()
//
//                }
//            }
//
//        })
//        usernameText.text = "$,\n $userid,\n $minibio, \n $location "
//    }

}