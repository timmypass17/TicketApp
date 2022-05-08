package com.example.ticketapp.ui.createTicket

import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ticketapp.data.models.Project
import com.example.ticketapp.data.models.User

@Composable
fun CreateTicketScreen(
    project: Project,
    user: User,
    onClickCreateTicket: (user: User, project: Project, title: String, description: String, projectTitle: String, priority: String) -> Unit
){
    Scaffold() {
        ticketBody(project, user, onClickCreateTicket)
    }
}

@Composable
fun ticketBody(
    project: Project,
    user: User,
    onClickCreateTicket: (user: User, project: Project, title: String, description: String, projectTitle: String, priority: String) -> Unit
){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var projectTitle by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Ticket Title:") }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Ticket Description:") }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = projectTitle,
            onValueChange = { projectTitle = it },
            label = { Text("Project:") }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = priority,
            onValueChange = { priority = it },
            label = { Text("Ticket priority:") }
        )
        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedButton(onClick = { onClickCreateTicket(user, project, title,description,projectTitle,priority)}) {
            Text("Create Ticket")
        }
    }
}

//
//@Composable
//fun createButton(onClickCreateTicket: (user: User, title: String, description: String, project: String, priority: String) -> Unit) {
//    OutlinedButton(onClick = { onClickCreateTicket(user, title, description,project,priority)}) {
//        Text("Create Ticket")
//    }
//}