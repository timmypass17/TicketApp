package com.example.ticketapp.ui.createProject

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketapp.data.models.User


@Composable
fun CreateProjectScreen(
    user: User,
    onClickCreateProject: (user: User, title: String, description: String, due: String) -> Unit){
    Scaffold() {
        projectBody(user, onClickCreateProject)
    }
}

@Composable
fun projectBody(user: User, onClickCreateProject: (user: User, title: String, description: String, due: String) -> Unit){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var due by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Text("Create Project", fontSize = 30.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Project Title:") }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Project Description:") }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = due,
            onValueChange = { due = it },
            label = { Text("Due Date:") }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        createProjectButton(onClickCreateProject = onClickCreateProject, user, title, description, due)
    }
}

@Composable
fun createProjectButton(onClickCreateProject: (user: User, title: String, description: String, due: String) -> Unit, user: User, title: String, description: String, due: String) {
    OutlinedButton(onClick = { onClickCreateProject(user, title,description, due)}) {
        Text("Create Project")
    }
}