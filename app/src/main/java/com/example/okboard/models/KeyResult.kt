package com.example.okboard.models

import android.os.Bundle
import androidx.annotation.ArrayRes

data class KeyResult(
    val id: Int,
    val progress: Double,
    val title: String?
){
    companion object {
        fun from(bundle: Bundle): KeyResult{
            return KeyResult(
                bundle.getInt("id"),
                bundle.getDouble("progress"),
                bundle.getString("title")
            )
        }
        fun fromList(bundle: Bundle): ArrayList<KeyResult>{
            val list: ArrayList<KeyResult>? = null

            list!!.add(KeyResult(
                bundle.getInt("id"),
                bundle.getDouble("progress"),
                bundle.getString("title")
            ))

            return list
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putInt("id", id)
        bundle.putDouble("progress", progress)
        bundle.putString("title", title)
        return bundle
    }
}