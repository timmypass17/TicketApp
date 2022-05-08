package com.example.ticketapp.ui.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ticketapp.data.models.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

const val TAG = "SignupViewModel"
class SignupViewModel() : ViewModel() {

    fun onClickSignUp(name: String, email: String, password: String, role: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                // Sign in success
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")

                    // Get current user info
                    val currentUser = Firebase.auth.currentUser

                    // Add user to database
                    if (currentUser != null) {
                        addUserToFirebase(currentUser, name, email, password, role)
                    }

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                }
            }
    }

    fun addUserToFirebase(
        user: FirebaseUser,
        name: String,
        email: String,
        password: String,
        role: String
    ) {
        val db = Firebase.firestore
        val docRef = db.document("users/${user?.uid}")
        // Check if user already in database
        docRef
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    Log.i(TAG, "User already in database")
                    return@addOnSuccessListener
                }
                // User not in database
                val userToAdd = User(
                    id = user.uid,
                    name = name,
                    email = email,
                    pass = password,
                    role = role,
                    projects = listOf()
                )
                docRef
                    .set(userToAdd)
                    .addOnSuccessListener { Log.i(TAG, "User added to database") }
                    .addOnFailureListener { Log.i(TAG, "Failed to add user") }
            }
    }
}