package com.example.okboard.activities

import android.app.ActionBar
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.okboard.R
import com.example.okboard.models.Objective
import kotlinx.android.synthetic.main.activity_read_objective.*

class ReadObjectiveActivity : AppCompatActivity() {

    //lateinit var  objTitleTextView:TextView
    
//    lateinit var keyResultsRecyclerView:RecyclerView
//    lateinit var keyResultsAdapter: keyResultsAdapter
//    lateinit var keyResultsLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_objective)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent=intent?:return
        val objective = Objective.from(intent.extras!!)

        objTitleTextView.text = objective.title

    }
}
