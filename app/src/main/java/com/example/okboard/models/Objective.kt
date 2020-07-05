package com.example.okboard.models

import android.os.Bundle
import com.example.okboard.responses.KeyResultsResponse
import java.io.Serializable
import java.lang.reflect.Array
import java.util.ArrayList

data class Objective(
    val id:Int,
    val title:String?,
    val key_result:ArrayList<KeyResultsResponse>? = null
): Serializable {

}