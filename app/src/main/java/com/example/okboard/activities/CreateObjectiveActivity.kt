package com.example.okboard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidnetworking.error.ANError
import com.example.okboard.R
import com.example.okboard.models.Objective
import com.example.okboard.networks.OkBoardApi
import com.example.okboard.responses.ObjectsResponse
import kotlinx.android.synthetic.main.activity_create_objective.*
import org.json.JSONObject
import java.io.Serializable

class CreateObjectiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_objective)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val result = this!!.getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
        val token = result.getString(getString(R.string.token), "")
        val userId = result.getInt(getString(R.string.user), 0)

        var url: String = OkBoardApi.PostObjectURL(userId!!)
        saveButton.setOnClickListener { view ->

            val title = objectiveEditText.text.toString()
            OkBoardApi.AddObject(
                url,
                token!!,
                title,
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
