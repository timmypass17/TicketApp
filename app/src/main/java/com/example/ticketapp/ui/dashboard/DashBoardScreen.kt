package com.example.ticketapp.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import com.example.ticketapp.data.models.Project
import com.example.ticketapp.data.models.User
import com.example.ticketapp.ui.projectDetail.user

@Composable
fun DashBoardScreen(
    user: User,
    onClickAddProject: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Welcome, ${user.name}!") },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onClickAddProject() }) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ){
        DashBoardBody(user = user)
    }
}

@Composable
fun DashBoardBody(user: User) {
    Column {
        Text("Project List")
        LazyColumn() {
            items(user.projects) { project ->
                ProjectItem(project)
            }
        }
    }
}

@Composable
fun ProjectItem(project: Project) {
    Row {
        Text(project.title)
    }
}