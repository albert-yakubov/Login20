package com.stepashka.buildinglocator2.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent


abstract class Util {



    companion object {
        fun isEmailValid(email: String): Boolean {
            return true
        }


        fun Context.startActivity(context: Context, clazz: Class<*>) {
            val intent = Intent(context, clazz)
            startActivity(intent)
        }

        fun showResetAlert(context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Empty Username")
            builder.setMessage("Please enter your username to continue")
            builder.setNegativeButton("OK") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            builder.show()
        }
        fun showWrongPasswordAlert(context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Check")
            builder.setMessage("Username or password!")
            builder.setNegativeButton("OK") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            builder.show()
        }
    }


}