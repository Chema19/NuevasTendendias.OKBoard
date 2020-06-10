package com.example.okboard.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.error.ANError
import com.example.okboard.R
import com.example.okboard.networks.OkBoardApi
import com.example.okboard.responses.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        startButton.setOnClickListener {
            var url: String = OkBoardApi.loginPost
            var usernameET = userEditText.text.toString()
            var passwordET = passwordEditText.text.toString()

            if(usernameET == "" && passwordET == ""){
                Toast.makeText(this, "Complete el campo usuario y contraseña", Toast.LENGTH_SHORT).show()
            }else {
                if (usernameET == "" || passwordET == "") {
                    Toast.makeText(this, "El campo usuario o contraseña esta vacio", Toast.LENGTH_SHORT).show()

                }else {
                    OkBoardApi.LoginUser(url, usernameET, passwordET,
                        { response -> handleResponse(response) },
                        { error -> handleError(error) })
                }
            }
        }


        registerButton.setOnClickListener {
            val intento = Intent(this, RegisterActivity::class.java)
            startActivity(intento)
        }
    }

    private fun handleResponse(response: LoginResponse?){

        val sharepreferences : SharedPreferences = getSharedPreferences(getString(R.string.string_preference),MODE_PRIVATE)
        val sp = sharepreferences.edit()

        sp.putString(getString(R.string.token), response!!.tokenType + " " + response!!.accessToken)
        sp.apply()

        if (sharepreferences.getBoolean(getString(R.string.string_preference), false)) {
            val localIntent = Intent(this, MainActivity::class.java)
            startActivity(localIntent)
            finish()
        }

        val intento = Intent(this, MainActivity::class.java)
        startActivity(intento)
    }

    private fun handleError(anError: ANError?){
        val message = JSONObject(anError!!.errorBody).get("Message").toString()

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
