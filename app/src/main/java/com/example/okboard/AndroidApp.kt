package com.example.okboard

import com.androidnetworking.AndroidNetworking
import com.orm.SugarApp

class AndroidApp : SugarApp(){
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(getApplicationContext())
    }
}