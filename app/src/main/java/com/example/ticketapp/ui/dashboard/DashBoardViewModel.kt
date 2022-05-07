package com.example.ticketapp.ui.dashboard

import android.hardware.usb.UsbRequest
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ticketapp.data.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

private const val TAG = "DashboardViewModel"
class DashBoardViewModel() : ViewModel() {

    var user: User by mutableStateOf(User())

    init {
        getCurrentUser()
    }

    fun getCurrentUser() {
        // Check if user already in firestore
        Log.i(TAG, "getting user data")
        val db = Firebase.firestore
        val docRef = db.document("users/${Firebase.auth.currentUser?.uid}")
        docRef
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    user = documentSnapshot.toObject<User>() ?: User()
                    Log.i(TAG, "Document Exists: $documentSnapshot")
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Document not found: $e")
            }
    }
}
