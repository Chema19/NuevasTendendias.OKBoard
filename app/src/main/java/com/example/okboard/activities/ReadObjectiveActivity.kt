package com.example.okboard.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.okboard.R
import com.example.okboard.adapters.KeyResultAdapter
import com.example.okboard.models.KeyResult
import com.example.okboard.models.Objective
import com.example.okboard.responses.ObjectsResponse
import kotlinx.android.synthetic.main.activity_read_objective.*

class ReadObjectiveActivity : AppCompatActivity() {

    var keyResults = ArrayList<KeyResult>()
    lateinit var keyResultRecyclerView: RecyclerView
    lateinit var keyResultAdapter: KeyResultAdapter
    lateinit var keyResultLayoutManager: RecyclerView.LayoutManager

    lateinit var addNewKeyResultButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_objective)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //val intent=intent?:return
        val objective = intent.getSerializableExtra("OBJ") as Objective
        objTitleTextView.text = objective.title

        objective.key_result!!.forEach {

            keyResults.add(KeyResult(it.id!!,it.progress!!,it.title))
        }


        keyResultRecyclerView = findViewById(R.id.keyresultsRecyclerView)
        keyResultAdapter = KeyResultAdapter(keyResults,this.applicationContext)
        keyResultLayoutManager = GridLayoutManager(this.applicationContext,1)

        keyResultRecyclerView.adapter = keyResultAdapter
        keyResultRecyclerView.layoutManager = keyResultLayoutManager

        val dividerItemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        keyResultRecyclerView.addItemDecoration(dividerItemDecoration)

        addNewKeyResultButton = findViewById(R.id.addKeyResultButton)
        addNewKeyResultButton.setOnClickListener { view ->
            val intent = Intent(this, CreateKeyResultActivity::class.java)
            intent.putExtra("objId",objective.id)
            startActivity(intent)
        }
    }
}
