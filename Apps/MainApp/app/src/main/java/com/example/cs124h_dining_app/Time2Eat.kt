package com.example.cs124h_dining_app

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class Time2Eat: Application() {
    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null
    override fun onCreate() {
        super.onCreate()
        // do stuff that we want to be available to all parts of our app here
        auth =  Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            user = currentUser
        }
    }
}