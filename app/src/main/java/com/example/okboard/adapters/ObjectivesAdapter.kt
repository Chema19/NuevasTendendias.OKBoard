package com.example.okboard.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.okboard.R
import com.example.okboard.activities.ReadObjectiveActivity
import com.example.okboard.models.Objective
import com.example.okboard.responses.ObjectsResponse
import kotlinx.android.synthetic.main.objective_item.view.*
import java.io.Serializable

class ObjectivesAdapter(var objectives:ArrayList<ObjectsResponse>, val context: Context) : RecyclerView.Adapter<ObjectivesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.objective_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return objectives.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var objective = objectives.get(position)
        holder.updateFrom(objective)
    }
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val objectiveLayout=view.objectiveLayout
        val titleTextView=view.objTitleTextView
        val descriptionTextView=view.objDescriptionTextView

        fun updateFrom(objective:ObjectsResponse){
            titleTextView.text = objective.title
            //descriptionTextView.text = objective.title

            objectiveLayout.setOnClickListener { view ->

                val context = view.context
                //Start Activity
                val intent = Intent(context,ReadObjectiveActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                val obj = Objective(objective.id!!,objective.title, objective.key_results)

                intent.putExtra("OBJ", Objective(objective.id!!,objective.title, objective.key_results) as Serializable)
                context.startActivity(intent)
            }
        }
    }
}