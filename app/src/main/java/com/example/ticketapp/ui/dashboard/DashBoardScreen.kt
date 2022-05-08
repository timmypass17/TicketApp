package com.example.ticketapp.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketapp.data.models.Project
import com.example.ticketapp.data.models.User
import com.example.ticketapp.ui.projectDetail.user

@Composable
fun DashBoardScreen(
    user: User,
    onClickAddProject: () -> Unit,
    onClickProject: (Project) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") },
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
        DashBoardBody(user = user, onClickProject)
    }
}

@Composable
fun DashBoardBody(user: User, onClickProject: (Project) -> Unit) {
    Column(Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("My Projects", fontSize = 30.sp)
            Spacer(Modifier.padding(4.dp))
            Text("(${user.projects.size})", fontSize = 25.sp)
        }
        Spacer(Modifier.padding(4.dp))
        Divider()
        LazyColumn() {
            items(user.projects) { project ->
                ProjectItem(project, onClickProject)
            }
        }
    }
}

@Composable
fun ProjectItem(
    project: Project,
    onClickProject: (Project) -> Unit
) {
    Column(
        Modifier
            .clickable { onClickProject(project) }
            .padding(8.dp)
            .fillMaxWidth(1f)) {
        Row {
            Column(Modifier.weight(1f)) {
                Text(project.title, fontSize = 20.sp)
            }
            Text("Due: ${project.due}", fontSize = 16.sp, modifier = Modifier.padding(top = 2.dp))
        }
        Spacer(modifier = Modifier.padding(2.dp))
        Text("By: ${project.manager.name}" , color = Color.Gray)
        Spacer(modifier = Modifier.padding(2.dp))
        Text("\"${project.description}\"", color = Color.Gray)
    }
    Divider()
}