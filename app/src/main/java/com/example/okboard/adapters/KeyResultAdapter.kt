package com.example.okboard.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.okboard.R
import com.example.okboard.activities.ReadKeyResultActivity
import com.example.okboard.models.KeyResult
import kotlinx.android.synthetic.main.key_result_item.view.*

class KeyResultAdapter(var keyResults:ArrayList<KeyResult>, val context: Context) : RecyclerView.Adapter<KeyResultAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.key_result_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return keyResults.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var keyResult = keyResults.get(position)
        holder.updateFrom(keyResult)
    }
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val keyResultLayout=view.keyResultLayout
        val keyTitleTextView = view.keyTitleTextView
        val keyProgressTextView=view.keyProgressTextView
        val keyProgressBar=view.keyProgressBar
        fun updateFrom(keyResult:KeyResult){
            keyTitleTextView.text = keyResult.title
            keyProgressTextView.text = keyResult.progress.toInt().toString();
            keyProgressBar.progress = keyResult.progress.toInt();

            keyResultLayout.setOnClickListener { view ->
                val context = view.context
                //Start Activity
                val intent = Intent(context,ReadKeyResultActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtras(keyResult.toBundle())
                context.startActivity(intent)
            }
        }
    }
}