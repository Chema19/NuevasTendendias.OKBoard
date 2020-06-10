package com.example.okboard.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.okboard.R

class CreateObjectiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_objective)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
