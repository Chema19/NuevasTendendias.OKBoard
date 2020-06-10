package com.example.okboard.models

import android.os.Bundle

data class Objective(
    val id:Int,
    val title:String?
){
    companion object {
        fun from(bundle: Bundle): Objective{
            return Objective(
                bundle.getInt("id"),
                bundle.getString("title")
            )
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putInt("id", id)
        bundle.putString("title", title)
        return bundle
    }
}