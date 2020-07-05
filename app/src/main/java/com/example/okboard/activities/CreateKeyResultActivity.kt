package com.example.okboard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.error.ANError
import com.example.okboard.R
import com.example.okboard.networks.OkBoardApi
import kotlinx.android.synthetic.main.activity_create_key_result.*

class CreateKeyResultActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_key_result)

        val objId = intent.getIntExtra("objId", 0)

        val result = this!!.getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
        val token = result.getString(getString(R.string.token), "")
        val userId = result.getInt(getString(R.string.user), 0)

        var url: String = OkBoardApi.PostKeyResultURL(userId!!,objId)
        saveNewKeyResultButton.setOnClickListener { view ->

            val title = newKeyResultEditText.text.toString()
            val progress = newKeyResultProgressEditText.text.toString().toDouble()
            OkBoardApi.AddKeyResult(
                url,
                token!!,
                title,
                progress,
                { response -> handleResponse(response) },
                { error -> handleError(error) })
        }
    }

    private fun handleResponse(response: String?){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    private fun handleError(anError: ANError?){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}
