package com.example.ticketapp.ui.projectDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton

@Composable
fun ProjectDetailScreen(){
    Scaffold (    floatingActionButton = {
        FloatingActionButton(onClick = { /* ... */ }) {
        }

    },floatingActionButtonPosition = FabPosition.End
    ){
        projectBody()
    }
}

@Composable
fun projectBody(){
    val innerPadding = PaddingValues(top = 10.dp, start = 15.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        Text("Title: ", fontSize = 30.sp)
        Text("Description: ",fontSize = 20.sp )
        UserList(users = users)
        ticketList(tickets = tickets)
    }
}

@Composable
fun UserList(users: List<user>){
    LazyColumn{
        items(users) { user ->
            Text(user.username, fontSize = 18.sp)
        }
    }
}

class user(name: String){
    val username = name
}

val users = listOf(user("john"), user("kim"), user("Timmy"))

@Composable
fun ticketList(tickets: List<ticket>){
    LazyColumn{
        items(tickets) { ticket ->
            Text(ticket.ticketName, fontSize = 18.sp)
        }
    }
}

class ticket(name: String){
    val ticketName = name
}

val tickets = listOf(ticket("ticket 1"),ticket("ticket 2"), ticket("ticket 4"))

@Composable
fun createTicketScreen(){

}

@Preview
@Composable
fun previewProject(){
    ProjectDetailScreen()
}

