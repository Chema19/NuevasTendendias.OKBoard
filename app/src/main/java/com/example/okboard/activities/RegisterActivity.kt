package com.example.okboard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.error.ANError
import com.example.okboard.R
import com.example.okboard.networks.OkBoardApi
import com.example.okboard.responses.RegisterResponse
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerProfileButton.setOnClickListener {
            var url: String = OkBoardApi.registerPost

            var credencialesET = credencialesEditText.text.toString()
            var constraseniaET = contraseniaEditText.text.toString()
            var constraseniaAgainET = contraseniaagainEditText.text.toString()

            if(constraseniaET != constraseniaAgainET) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }else{
                OkBoardApi.AddUser(url,credencialesET,
                    constraseniaET,
                    { response -> handleResponse(response) },
                    { error -> handleError(error) })
            }
        }
    }

    private fun handleResponse(Response: RegisterResponse?){
        Toast.makeText(this, Response!!.message, Toast.LENGTH_SHORT).show()
        val intento = Intent(this, LoginActivity::class.java)
        startActivity(intento)
    }

    private fun handleError(anError: ANError?){
        //Log.d("Respuesta Falsa", anError!!.message)
        val message = JSONObject(anError!!.errorBody).get("message").toString()

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
