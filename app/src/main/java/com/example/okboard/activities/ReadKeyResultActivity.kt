package com.example.okboard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.okboard.R
import com.example.okboard.models.KeyResult
import kotlinx.android.synthetic.main.activity_read_key_result.*

class ReadKeyResultActivity : AppCompatActivity() {

    lateinit var deleteButton:Button
    lateinit var keyResultTitleTextView:TextView
    lateinit var keyResultProgressTextView:TextView
    lateinit var keyResultProgressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_key_result)

        keyResultTitleTextView = keyTitleTextView
        keyResultProgressTextView = keyProgressTextView
        keyResultProgressBar = keyProgressBar

        val intent=intent?:return
        val keyResult = KeyResult.from(intent.extras!!)
        keyResultTitleTextView.text = keyResult.title
        keyResultProgressTextView.text = keyResult.progress.toInt().toString()
        keyResultProgressBar.progress = keyResult.progress.toInt()

        deleteButton = deleteKeyResultButton
        deleteButton.setOnClickListener { view ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Eliminar Key Result")
            builder.setMessage("¿Está seguro que desea eliminar este Key Result?")

            builder.setPositiveButton("SI") { dialog, which ->
                Toast.makeText(this, "Key Result Eliminado", Toast.LENGTH_SHORT).show()
                finish()
            }

            builder.setNegativeButton("NO") { dialog, which ->
            }

            builder.show()
            }
    }
}
