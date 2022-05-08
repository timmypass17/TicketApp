package com.example.ticketapp.ui.projectDetail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ticketapp.data.models.Project
import com.example.ticketapp.data.models.Ticket
import com.example.ticketapp.data.models.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

private val TAG = "ProjectDetailViewModel"
class ProjectDetailViewModel() : ViewModel() {
    var project: Project by mutableStateOf(Project())
    var tickets: List<Ticket> by mutableStateOf(listOf())

    fun getCurrentProject(id: String) {
        val db = Firebase.firestore
        val docRef = db.document("projects/${id}")
        Log.i(TAG, "Calling getCu")

        docRef
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    project = documentSnapshot.toObject<Project>() ?: Project()
                    Log.i(TAG, "Got new document from clicked: $documentSnapshot")
                    Log.i(TAG, "project: $project")

                    tickets = project.tickets
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Did not get document from clicked: $e")
            }
    }

    fun addTicketToFirebase(
        user: User,
        project: Project,
        title: String,
        description: String,
        projectTitle: String,
        priority: String,
    ) {
        // Get database
        val db = Firebase.firestore
        Log.i("createTicketViewModel", "adding ticket to firebase")
        // Update projects's ticket list
        val docRef = db.document("projects/${project.id}")
        docRef
            .get()
            .addOnSuccessListener { documentSnapshot->
                // get current project's tickets list
                val currProject = documentSnapshot.toObject<Project>()
                val currTickets = currProject?.tickets?.toMutableList()

                // Create ticket to add to database
                val ticketToAdd = Ticket(
                    title = title,
                    description = description,
                    project = projectTitle,
                    priority = priority,
                    status = "open",
                    createdAt = "5/8/2022",
                    author = user.name
                )

                currTickets?.add(ticketToAdd)
                docRef
                    .update("tickets", currTickets)

                if (currTickets != null) {
                    tickets = currTickets
                }
            }
    }
}