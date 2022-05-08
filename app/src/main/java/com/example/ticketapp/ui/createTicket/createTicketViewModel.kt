package com.example.ticketapp.ui.createTicket

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ticketapp.data.models.Project
import com.example.ticketapp.data.models.Ticket
import com.example.ticketapp.data.models.User
import com.example.ticketapp.ui.signup.TAG
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class createTicketViewModel() : ViewModel(){
//    fun addTicketToFirebase(
//        project: Project,
//        title: String,
//        description: String,
//        projectTitle: String,
//        priority: String,
//    ) {
//        // Get database
//        val db = Firebase.firestore
//        Log.i("createTicketViewModel", "adding ticket to firebase")
//        // Update projects's ticket list
//        val docRef = db.document("projects/${project.id}")
//        docRef
//            .get()
//            .addOnSuccessListener { documentSnapshot->
//                // get current project's tickets list
//                val currProject = documentSnapshot.toObject<Project>()
//                val currTickets = currProject?.tickets?.toMutableList()
//
//                // Create ticket to add to database
//                val ticketToAdd = Ticket(
//                    title = title,
//                    description = description,
//                    project = projectTitle,
//                    priority = priority,
//                    status = "",
//                    createdAt = ""
//                )
//
//                currTickets?.add(ticketToAdd)
//                docRef
//                    .update("tickets", currTickets)
//            }
//    }

}