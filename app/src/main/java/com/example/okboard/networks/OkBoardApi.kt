package com.example.okboard.networks

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.okboard.models.Objective
import com.example.okboard.responses.LoginResponse
import com.example.okboard.responses.ObjectsResponse
import com.example.okboard.responses.RegisterResponse
import org.json.JSONException

import org.json.JSONObject




class OkBoardApi {
    companion object {

        //URLs
        val baseUrl = "https://okrboard.herokuapp.com"
        val loginPost = "$baseUrl/auth/signin"
        val registerPost = "$baseUrl/auth/signup"


        val ObjectsGet = "$baseUrl/users/{userId}/objectives"

        fun GetObjectURL(userId: Int): String{
            return "${baseUrl}/users/" + userId.toString() + "/objectives"
        }
        fun PostObjectURL(userId: Int): String{
            return "${baseUrl}/users/" + userId.toString() + "/objectives"
        }

        fun GetPutDeletetObjectURLById(userId: Int, objectId: Int): String{
            return "${baseUrl}/users/" + userId.toString() + "/objectives/" + objectId.toString()
        }

        fun PostKeyResultURL(userId: Int, objectId: Int): String{
            return "${baseUrl}/users/" + userId.toString() + "/objectives/" + objectId.toString() + "/keyresults/"
        }

        fun GetPutDeletetKeyResultURLById(userId: Int, objectId: Int, keyResult: Int): String{
            return "${baseUrl}/users/" + userId.toString() + "/objectives/" + objectId.toString() + "/keyresults/" + keyResult.toString()
        }


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

        fun ListObject(
            url: String,
            key: String,
            responseHandler: (ArrayList<ObjectsResponse>?) -> Unit, errorHandler: (ANError?) -> Unit){

            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObjectList(ObjectsResponse::class.java,
                    object : ParsedRequestListener<ArrayList<ObjectsResponse>> {
                        override fun onResponse(response: ArrayList<ObjectsResponse>?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError) {
                            errorHandler(anError)
                        }
                    })

        }



        fun AddObject(
            url: String,
            key: String,
            title: String,
            responseHandler: (String?) -> Unit, errorHandler: (ANError?) -> Unit
        ) {

            val jsonObject = JSONObject()
            try {
                jsonObject.put("title", title)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.post(url)
                //.addBodyParameter("email", credenciales)
                //.addBodyParameter("password", contrasenia)
                .addHeaders("Authorization", key)
                .addJSONObjectBody(jsonObject)
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(String::class.java,
                    object : ParsedRequestListener<String> {
                        override fun onResponse(response: String?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError) {
                            errorHandler(anError)
                        }
                    })
        }

        fun EditObject(
            url: String,
            key: String,
            title: String,
            responseHandler: (ObjectsResponse?) -> Unit, errorHandler: (ANError?) -> Unit
        ) {

            val jsonObject = JSONObject()
            try {
                jsonObject.put("title", title)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.put(url)
                //.addBodyParameter("email", credenciales)
                //.addBodyParameter("password", contrasenia)
                .addHeaders("Authorization", key)
                .addJSONObjectBody(jsonObject)
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ObjectsResponse::class.java,
                    object : ParsedRequestListener<ObjectsResponse> {
                        override fun onResponse(response: ObjectsResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError) {
                            errorHandler(anError)
                        }
                    })
        }

        fun DeleteObject(
            url: String,
            key: String,
            title: String,
            responseHandler: (String?) -> Unit, errorHandler: (ANError?) -> Unit
        ) {

            val jsonObject = JSONObject()
            try {
                jsonObject.put("title", title)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.delete(url)
                //.addBodyParameter("email", credenciales)
                //.addBodyParameter("password", contrasenia)
                .addHeaders("Authorization", key)
                .addJSONObjectBody(jsonObject)
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(String::class.java,
                    object : ParsedRequestListener<String> {
                        override fun onResponse(response: String?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError) {
                            errorHandler(anError)
                        }
                    })
        }

        fun AddKeyResult(
            url: String,
            key: String,
            title: String,
            progress: Double,
            responseHandler: (String?) -> Unit, errorHandler: (ANError?) -> Unit
        ) {

            val jsonObject = JSONObject()
            try {
                jsonObject.put("title", title)
                jsonObject.put("progress", progress)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.post(url)
                .addHeaders("Authorization", key)
                .addJSONObjectBody(jsonObject)
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(String::class.java,
                    object : ParsedRequestListener<String> {
                        override fun onResponse(response: String?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError) {
                            errorHandler(anError)
                        }
                    })
        }

        fun EditKeyResult(
            url: String,
            key: String,
            title: String,
            responseHandler: (ObjectsResponse?) -> Unit, errorHandler: (ANError?) -> Unit
        ) {

            val jsonObject = JSONObject()
            try {
                jsonObject.put("title", title)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.put(url)
                //.addBodyParameter("email", credenciales)
                //.addBodyParameter("password", contrasenia)
                .addHeaders("Authorization", key)
                .addJSONObjectBody(jsonObject)
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ObjectsResponse::class.java,
                    object : ParsedRequestListener<ObjectsResponse> {
                        override fun onResponse(response: ObjectsResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError) {
                            errorHandler(anError)
                        }
                    })
        }

        fun DeleteKeyResult(
            url: String,
            key: String,
            title: String,
            responseHandler: (String?) -> Unit, errorHandler: (ANError?) -> Unit
        ) {

            val jsonObject = JSONObject()
            try {
                jsonObject.put("title", title)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.delete(url)
                //.addBodyParameter("email", credenciales)
                //.addBodyParameter("password", contrasenia)
                .addHeaders("Authorization", key)
                .addJSONObjectBody(jsonObject)
                .setTag("RelatoricaApp")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(String::class.java,
                    object : ParsedRequestListener<String> {
                        override fun onResponse(response: String?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError) {
                            errorHandler(anError)
                        }
                    })
        }
    }
}