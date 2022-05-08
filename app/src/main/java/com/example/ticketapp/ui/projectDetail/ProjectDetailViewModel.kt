package com.example.ticketapp.ui.projectDetail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ticketapp.data.models.Project
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

private val TAG = "ProjectDetailViewModel"
class ProjectDetailViewModel() : ViewModel() {
    var project: Project by mutableStateOf(Project())

    fun getCurrentProject(id: String) {
        val db = Firebase.firestore
        val docRef = db.document("project/${id}")
        Log.i(TAG, "Calling getCu")

        docRef
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    project = documentSnapshot.toObject<Project>() ?: Project()
                    Log.i(TAG, "Got new document from clicked: $documentSnapshot")
                    Log.i(TAG, "project: $project")
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Did not get document from clicked: $e")
            }
    }
}