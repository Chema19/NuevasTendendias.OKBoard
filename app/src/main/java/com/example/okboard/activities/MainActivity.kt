package com.example.okboard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.okboard.R
import com.example.okboard.adapters.ObjectivesAdapter
import com.example.okboard.models.Objective
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    var objectives = ArrayList<Objective>()
    lateinit var objectivesRecyclerView:RecyclerView
    lateinit var objectivesAdapter: ObjectivesAdapter
    lateinit var objectivesLayoutManager: RecyclerView.LayoutManager

    lateinit var  fabCreateObjective:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        objectives.add(Objective(1,"Prueba 1"))
        objectives.add(Objective(2,"Prueba 2"))
        objectives.add(Objective(3,"Prueba 3"))
        objectives.add(Objective(4,"Prueba 4"))
        objectives.add(Objective(4,"Prueba 4"))

        objectivesRecyclerView = findViewById(R.id.objectivesRecyclerView)
        objectivesAdapter = ObjectivesAdapter(objectives,this.applicationContext)
        objectivesLayoutManager = GridLayoutManager(this.applicationContext,1)

        objectivesRecyclerView.adapter = objectivesAdapter
        objectivesRecyclerView.layoutManager = objectivesLayoutManager

        fabCreateObjective = findViewById(R.id.addObjectiveFab)
        fabCreateObjective.setOnClickListener { view ->
            val intent = Intent(this, CreateObjectiveActivity::class.java)
            startActivity(intent)
        }
    }
}
