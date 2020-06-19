package com.example.okboard.models

import android.os.Bundle

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
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putInt("id", id)
        bundle.putDouble("progress", progress)
        bundle.putString("title", title)
        return bundle
    }
}