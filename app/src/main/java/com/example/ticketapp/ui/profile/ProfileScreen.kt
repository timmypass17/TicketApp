package com.example.ticketapp.ui.profile

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen(
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TicketApp") },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ){
        ProfileBody()
    }
}

@Composable
fun ProfileBody() {
    Text("DashBoard Screen")

}