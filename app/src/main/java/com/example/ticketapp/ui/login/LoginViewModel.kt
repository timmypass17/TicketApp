package com.example.ticketapp.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private val TAG = "LoginViewModel"
class LoginViewModel() : ViewModel() {

    val auth = Firebase.auth
    var isLoggedIn by mutableStateOf(auth.currentUser != null) // check if app has user

    fun onClickLogin(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(com.example.ticketapp.ui.signup.TAG, "onClickLogin:success")
                    isLoggedIn = true
                } else {
                    Log.w(com.example.ticketapp.ui.signup.TAG, "onClickLogin:failure", task.exception)
                    isLoggedIn = false
                }
            }
    }
}