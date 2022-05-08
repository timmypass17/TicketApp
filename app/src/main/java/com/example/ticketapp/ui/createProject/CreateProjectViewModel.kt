package com.example.ticketapp.ui.createProject

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ticketapp.data.models.Project
import com.example.ticketapp.data.models.User
import com.example.ticketapp.ui.signup.TAG
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class createProjectViewModel() : ViewModel(){
    fun onClickCreateProject(title: String, description: String){
        // Get database
        val db = Firebase.firestore
        // Generate random id
        val id = db.collection("projects").document().id

        // Create project
        val projectToAdd = Project(title, description, User(), listOf(), listOf(), "")

        // Add to database
        db.collection("projects").document(id).set(projectToAdd)
    }
}