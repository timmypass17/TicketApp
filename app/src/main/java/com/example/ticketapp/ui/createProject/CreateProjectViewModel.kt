package com.example.ticketapp.ui.createProject

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ticketapp.data.models.Project
import com.example.ticketapp.data.models.User
import com.example.ticketapp.ui.projectDetail.user
import com.example.ticketapp.ui.signup.TAG
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class createProjectViewModel() : ViewModel(){
    fun onClickCreateProject(user: User, title: String, description: String, due: String){
        // Get database
        val db = Firebase.firestore

        // Generate random id
        val id = db.collection("projects").document().id

        // Create project
        val projectToAdd = Project(
            title = title,
            description = description,
            manager = user,
            tickets = listOf(),
            users = listOf(),
            createdAt = "",
            due = due
        )

        // Add to database
        db.collection("projects").document(id).set(projectToAdd)

        // Update user's project list
        val docRef = db.document("users/${user.id}")
        docRef
            .get()
            .addOnSuccessListener { documentSnapshot->
                // Get current user and update user's list of project
                val currentUser = documentSnapshot.toObject<User>()
                val currentProjects = currentUser?.projects
                val newListOfProjects = currentProjects?.toMutableList()
                newListOfProjects?.add(projectToAdd)

                if (newListOfProjects != null) {
                    currentUser.projects = newListOfProjects
                }
                docRef
                    .update("projects", newListOfProjects)
            }
    }
}