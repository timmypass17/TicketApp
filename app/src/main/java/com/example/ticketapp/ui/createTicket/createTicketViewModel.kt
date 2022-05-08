package com.example.ticketapp.ui.createTicket

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ticketapp.data.models.Ticket
import com.example.ticketapp.data.models.User
import com.example.ticketapp.ui.signup.TAG
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class createTicketViewModel() : ViewModel(){
    fun onClickCreateTicket (title: String, description: String, project: String, priority: String){

    }
//    fun addTicketToFirebase(
//        user: FirebaseUser,
//        title: String,
//        description: String,
//        project: String,
//        status: String,
//        priority: String,
//        createdAt: String
//    ) {
//        val db = Firebase.firestore
//        val docRef = db.document("users/${user?.uid}")
//        // Check if user already in database
//        docRef
//            .get()
//            .addOnSuccessListener { documentSnapshot ->
//                if (documentSnapshot.exists()) {
//                    Log.i(TAG, "User already in database")
//                    return@addOnSuccessListener
//                }
//                // User not in database
//                val ticketToAdd = Ticket(
////                    id = user.uid,
//                    title = title,
//                    description = description,
//                    project = project,
//                    status = status,
//                    priority = priority,
//                    createdAt = createdAt
//                )
//                docRef
//                    .set(userToAdd)
//                    .addOnSuccessListener { Log.i(TAG, "Ticket added to database") }
//                    .addOnFailureListener { Log.i(TAG, "Failed to add ticket") }
//            }
//    }

}