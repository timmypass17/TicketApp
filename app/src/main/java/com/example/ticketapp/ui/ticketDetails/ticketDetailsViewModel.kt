package com.example.ticketapp.ui.ticketDetails
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ticketapp.data.models.Project
import com.example.ticketapp.data.models.Ticket
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firestore.admin.v1.Index

class ticketDetailsViewModel(): ViewModel() {
    var ticket: Ticket by mutableStateOf(Ticket())
    val db = Firebase.firestore
    var currProject: Project by mutableStateOf(Project())
    val currTicketIndex: Int by mutableStateOf(0)

    fun getCurrentTicket(project: Project, index: Int) {
        val currTickets = project.tickets
        ticket = currTickets[index]
    }

    fun onClickReadyButton() {
        // only developer has access to this button
        // this button sets the ticket status to "ready for review"
        val docRef = db.document("projects/${currProject.id}")

        docRef
            .get()
            .addOnSuccessListener { documentSnapshot ->
                // get current project from database and update ticket status to "ready for review"
                val currProject = documentSnapshot.toObject<Project>()
                val currTickets = currProject?.tickets
                currTickets?.get(currTicketIndex)?.status = "Ready"
                docRef
                    .update("tickets", currTickets)
            }

    }

    fun onClickCloseButton() {
        // only the project manager has access to this button
        // this button sets the ticket status to "closed"
        val docRef = db.document("projects/${currProject.id}")

        docRef
            .get()
            .addOnSuccessListener { documentSnapshot ->
                // get current project from database and update ticket status to "closed"
                val currProject = documentSnapshot.toObject<Project>()
                val currTickets = currProject?.tickets
                currTickets?.get(currTicketIndex)?.status = "Closed"
                docRef
                    .update("tickets", currTickets)
            }
    }

    fun onClickPriorityButton() {
        // this button is available for every role
        // sets the ticket priority to user input

        //SimpleAlertDialog()

    }

    fun getCurrentProjectDetails(id: String) {
        val db = Firebase.firestore
        val docRef = db.document("projects/${id}")

        docRef
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    currProject = documentSnapshot.toObject<Project>() ?: Project()
                }
            }
            .addOnFailureListener { e ->
            }
    }
}