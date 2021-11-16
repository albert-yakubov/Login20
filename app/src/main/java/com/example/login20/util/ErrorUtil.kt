package com.example.login20.util

import com.example.login20.loginMVVMnetwork.ApiClient
import com.stepashka.buildinglocator2.models.ErrorResponce

import retrofit2.Response
import java.io.IOException

object ErrorUtils {

    fun parseError(response: Response<*>): ErrorResponce? {
        val conversorDeErro = ApiClient.apiClient()
            .responseBodyConverter<ErrorResponce>(ErrorResponce::class.java, arrayOfNulls(0))
        var errorResponce: ErrorResponce? = null
        try {
            if (response.errorBody() != null) {
                errorResponce = conversorDeErro.convert(response.errorBody()!!)
            }
        } catch (e: IOException) {
            return ErrorResponce()
        } finally {
            return errorResponce
        }
    }

}