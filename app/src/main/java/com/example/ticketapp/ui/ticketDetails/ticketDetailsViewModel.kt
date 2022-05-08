package com.example.ticketapp.ui.ticketDetails
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.ticketapp.data.models.Project
import com.example.ticketapp.data.models.Ticket
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ticketDetailsViewModel(): ViewModel() {
    val ticket: Ticket = TODO();
    val db = Firebase.firestore
    val currProject: Project by mutableStateOf(Project())
    val currTicketIndex: Int by mutableStateOf(0)
    val docRef = db.document("projects/${currProject}")

    fun onClickReadyButton() {
        // only developer has access to this button
        // this button sets the ticket status to "ready for review"
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
}



/*

    object Priority {
    val none = "None"
    val low = "Low"
    val mid = "Mid"
    val high = "High"
}


Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val selectedPriority = remember {
            mutableStateOf("")
        }

        Text(text = "Select priority", fontSize = 18.sp)
        Spacer(modifier = Modifier.size(16.dp))

        Row {
            RadioButton(
                selected = selectedPriority.value == Priority.none,
                onClick = { selectedPriority.value == Priority.none}
            )
            Spacer (modifier = Modifier.size(16.dp))
            Text(Priority.none)

            RadioButton(
                selected = selectedPriority.value == Priority.low,
                onClick = { selectedPriority.value == Priority.low}
            )
            Spacer (modifier = Modifier.size(16.dp))
            Text(Priority.low)

            RadioButton(
                selected = selectedPriority.value == Priority.mid,
                onClick = { selectedPriority.value == Priority.mid}
            )
            Spacer (modifier = Modifier.size(16.dp))
            Text(Priority.mid)

            RadioButton(
                selected = selectedPriority.value == Priority.high,
                onClick = { selectedPriority.value == Priority.high}
            )
            Spacer (modifier = Modifier.size(16.dp))
            Text(Priority.high)
        }
    }
 */