package com.example.ticketapp.ui.ticketDetails

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketapp.data.models.Project
import com.example.ticketapp.data.models.Ticket
import com.example.ticketapp.data.models.User
import com.example.ticketapp.ui.theme.Purple700
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Composable
fun ticketDetailScreen (
    // the current ticket to be displayed onto the screen
    currTicket: Ticket,
    onClickReadyButton: () -> Unit, // only developers will have access to this button
    onClickCloseButton: () -> Unit, // only project managers will have access to this button
    onClickPriorityButton: () -> Unit
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("") },
                backgroundColor = androidx.compose.ui.graphics.Color.White,
                navigationIcon = {
                    IconButton(onClick = { onClickReadyButton() }) {
                        Icon(Icons.Filled.Done, contentDescription = "Ready Button")
                    }
                    IconButton(onClick = { onClickCloseButton() }) {
                        Icon(Icons.Filled.Done, contentDescription = "Close Button")
                    }
                    IconButton(onClick = { onClickPriorityButton() }) {
                        Icon(Icons.Filled.Star, contentDescription = "Priority Button")
                    }
                }
            )
        }
    ) {
        ticketDetailBody(currTicket)
    }
}

@Composable
fun ticketDetailBody(currTicket: Ticket) {
    Column (modifier = Modifier
        .padding(all = 8.dp)) {

        Text(text = "Title ", fontWeight = FontWeight.Bold, color = Purple700)
        Text(text = currTicket.title, modifier = Modifier.padding(all = 3.dp))

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Description ", fontWeight = FontWeight.Bold, color = Purple700)
        Text(text = currTicket.description, modifier = Modifier.padding(all = 3.dp))

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Status ", fontWeight = FontWeight.Bold, color = Purple700)
        Text(text = currTicket.status, modifier = Modifier.padding(all = 3.dp))

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Priority ", fontWeight = FontWeight.Bold, color = Purple700)
        Text(text = currTicket.priority, modifier = Modifier.padding(all = 3.dp))

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Date created ", fontWeight = FontWeight.Bold, color = Purple700)
        Text(text = currTicket.createdAt, modifier = Modifier.padding(all = 3.dp))
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ticketDetailScreenPreview() {
//    ticketDetailBody(Ticket("Testing ticket","This is a temporary ticket",  "Open", "Low", "01-01-2011"))
//}

object Priority {
    val none = "None"
    val low = "Low"
    val mid = "Mid"
    val high = "High"
}

@Composable
fun SimpleAlertDialog() {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = {})
            { Text(text = "OK")}
        },
        dismissButton = {
            TextButton(onClick = {})
            { Text(text = "Cancel") }
        },
        title = { Text(text = "Select priority", color = Purple700) },
    )


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

            Text(Priority.none)
            Spacer (modifier = Modifier.size(16.dp))

            RadioButton(
                selected = selectedPriority.value == Priority.low,
                onClick = { selectedPriority.value == Priority.low}
            )
            Text(Priority.low)
            Spacer (modifier = Modifier.size(16.dp))

            RadioButton(
                selected = selectedPriority.value == Priority.mid,
                onClick = { selectedPriority.value == Priority.mid}
            )
            Text(Priority.mid)
            Spacer (modifier = Modifier.size(16.dp))

            RadioButton(
                selected = selectedPriority.value == Priority.high,
                onClick = { selectedPriority.value == Priority.high}
            )
            Text(Priority.high)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleAlertDialogPreview() {
    SimpleAlertDialog()
}




