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

        val intent=intent?:return
        val objective = Objective.from(intent.extras!!)
        objTitleTextView.text = objective.title

        keyResults.add(KeyResult(1,10.0,"Prueba 1"))
        keyResults.add(KeyResult(2,25.0,"Prueba 2"))
        keyResults.add(KeyResult(3,50.0,"Prueba 3"))
        keyResults.add(KeyResult(4,75.0,"Prueba 4"))
        keyResults.add(KeyResult(5,100.0,"Prueba 5"))
        keyResults.add(KeyResult(5,15.0,"Prueba 7"))
        keyResults.add(KeyResult(5,75.5,"Prueba 8"))
        keyResults.add(KeyResult(5,38.0,"Prueba 9"))
        keyResults.add(KeyResult(5,29.0,"Prueba 10"))

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
            startActivity(intent)
        }
    }
}
