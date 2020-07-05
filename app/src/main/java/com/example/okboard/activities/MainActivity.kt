package com.example.okboard.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.error.ANError
import com.example.okboard.R
import com.example.okboard.adapters.ObjectivesAdapter
import com.example.okboard.models.Objective
import com.example.okboard.networks.OkBoardApi
import com.example.okboard.responses.LoginResponse
import com.example.okboard.responses.ObjectsResponse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var objectives = ArrayList<ObjectsResponse>()
    lateinit var objectivesRecyclerView:RecyclerView
    lateinit var objectivesAdapter: ObjectivesAdapter
    lateinit var objectivesLayoutManager: RecyclerView.LayoutManager
    private var token: String? = null
    private var userId: Int? = 0

    lateinit var  fabCreateObjective:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result = this!!.getSharedPreferences(getString(R.string.string_preference), AppCompatActivity.MODE_PRIVATE)
        token = result.getString(getString(R.string.token), "")
        userId = result.getInt(getString(R.string.user), 0)



        /*objectives.add(Objective(1,"Prueba 1"))
        objectives.add(Objective(2,"Prueba 2"))
        objectives.add(Objective(3,"Prueba 3"))
        objectives.add(Objective(4,"Prueba 4"))
        objectives.add(Objective(4,"Prueba 4"))*/

        objectives = ArrayList<ObjectsResponse>()
        objectivesRecyclerView = findViewById(R.id.objectivesRecyclerView)
        objectivesAdapter = ObjectivesAdapter(objectives,this.applicationContext)
        objectivesLayoutManager = GridLayoutManager(this.applicationContext,1)
        objectivesRecyclerView.adapter = objectivesAdapter
        objectivesRecyclerView.layoutManager = objectivesLayoutManager

        var url: String = OkBoardApi.GetObjectURL(userId!!)

        OkBoardApi.ListObject(url, token!!, {response -> handleResponse(response) },{ error -> handleError(error) })

        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        objectivesRecyclerView.addItemDecoration(dividerItemDecoration)

        fabCreateObjective = findViewById(R.id.addObjectiveFab)
        fabCreateObjective.setOnClickListener { view ->
            val intent = Intent(this, CreateObjectiveActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleResponse(response: ArrayList<ObjectsResponse>?){
        objectives = response!!
        objectivesAdapter.objectives = objectives
        objectivesAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?){
        val message = JSONObject(anError!!.errorBody).get("Message").toString()

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
