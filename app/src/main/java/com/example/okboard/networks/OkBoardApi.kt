package com.example.okboard.networks

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.okboard.responses.LoginResponse
import com.example.okboard.responses.RegisterResponse
import org.json.JSONException

import org.json.JSONObject




class OkBoardApi {
    companion object {

        //URLs
        val baseUrl = "https://okrboard.herokuapp.com"
        val loginPost = "$baseUrl/auth/signin"
        val registerPost = "$baseUrl/auth/signup"

        fun LoginUser(
            url: String,
            credenciales: String,
            contrasenia: String,
            responseHandler: (LoginResponse?) -> Unit, errorHandler: (ANError?) -> Unit
        ) {


            val jsonObject = JSONObject()
            try {
                jsonObject.put("email", credenciales)
                jsonObject.put("password", contrasenia)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.post(url)
                //.addBodyParameter("email", credenciales)
                //.addBodyParameter("password", contrasenia)
                .addJSONObjectBody(jsonObject)
                //.setContentType("application/json; charset=utf-8")
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(
                    LoginResponse::class.java,
                    object : ParsedRequestListener<LoginResponse> {
                        override fun onResponse(response: LoginResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError) {
                            errorHandler(anError)
                        }
                    })
        }

        fun AddUser(
            url: String,
            credenciales: String,
            contrasenia: String,
            responseHandler: (RegisterResponse?) -> Unit, errorHandler: (ANError?) -> Unit
        ) {

            val jsonObject = JSONObject()
            try {
                jsonObject.put("email", credenciales)
                jsonObject.put("password", contrasenia)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.post(url)
                //.addBodyParameter("email", credenciales)
                //.addBodyParameter("password", contrasenia)
                .addJSONObjectBody(jsonObject)
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(RegisterResponse::class.java,
                    object : ParsedRequestListener<RegisterResponse> {
                        override fun onResponse(response: RegisterResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError) {
                            errorHandler(anError)
                        }
                    })
        }
    }
}