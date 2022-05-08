package com.example.ticketapp.ui.projectDetail

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import com.example.ticketapp.data.models.Project
import com.example.ticketapp.data.models.Ticket
import com.example.ticketapp.ui.ticketDetails.ticketDetailBody

@Composable
fun ProjectDetailScreen(
    project: Project, tickets: List<Ticket>,
    onClickCreateTicketFAB: (Project) -> Unit,
    onClickTicket: (Int) -> Unit
){
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(project.title) },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Ready Button")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onClickCreateTicketFAB(project) }) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ){
        projectBody(project, tickets, onClickTicket)
    }
    Log.i("ProjectDetailScreen", "List of tickets ${tickets})")
}

@Composable
fun projectBody(
    project: Project,
    tickets: List<Ticket>,
    onClickTicket: (Int) -> Unit
){
    val innerPadding = PaddingValues(top = 10.dp, start = 15.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Text("Title: ${project.title}", fontSize = 30.sp)
        Text("Description: ",fontSize = 20.sp )
//        UserList(users = users)
        ticketList(tickets = tickets, onClickTicket)
    }
}

//@Composable
//fun UserList(users: List<user>){
//    LazyColumn{
//        items(users) { user ->
//            Text(user.username, fontSize = 18.sp)
//        }
//    }
//}

@Composable
fun ticketList(
    tickets: List<Ticket>,
    onClickTicket: (Int) -> Unit
){
    LazyColumn{
        itemsIndexed(tickets) { index, ticket ->
            Row(Modifier.clickable { onClickTicket(index) }
            ) {
                TicketItem(ticket = ticket)
            }
        }
    }
}

@Composable
fun TicketItem(ticket: Ticket){
    Column() {
        Spacer(modifier = Modifier.padding(4.dp))
        Row() {
            Column(Modifier.weight(1f)) {
                Text(ticket.title, fontSize = 18.sp)
                Text("By: ${ticket.author}", color = Color.Gray)
            }
            Text(
                text = ticket.status,
                Modifier.padding(8.dp),
                color = if (ticket.status == "open") {
                    Color.Green
                } else if (ticket.status == "review") {
                    Color.Yellow
                } else {
                    Color.Gray
                }
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Divider()
    }
}

//@Preview
//@Composable
//fun previewProject(){
//    ProjectDetailScreen()
//}

