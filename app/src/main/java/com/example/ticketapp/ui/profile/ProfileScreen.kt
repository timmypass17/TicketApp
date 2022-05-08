package com.example.ticketapp.ui.profile

import androidx.compose.foundation.layout.*
import com.example.ticketapp.R
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ticketapp.data.models.User

@Composable
fun ProfileScreen(
    user: User
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Profile") },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                }
            )
        }
    ){
        ProfileBody(user = user)
    }
}

@Composable
fun ProfileBody(user: User) {
    Row(Modifier.padding(8.dp)) {
        Icon(
            Icons.Default.Person,
            contentDescription = null, // decorative element
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterVertically)
        )
        Spacer(Modifier.padding(4.dp))
        Column(Modifier.padding(8.dp)) {
            Text("Username: ${user.name}")
            Text("Email: ${user.email}")
            Text("Role: ${user.role}")

        }
    }
}