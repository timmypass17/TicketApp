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
import com.example.ticketapp.data.models.User

@Composable
fun CreateProjectScreen(onClickCreateProject: (title: String, description: String) -> Unit){
    Scaffold() {
        projectBody(onClickCreateProject)
    }
}

@Composable
fun createProjectButton(onClickCreateProject: (title: String, description: String) -> Unit, title: String, description: String) {
    OutlinedButton(onClick = { onClickCreateProject(title,description)}) {
        Text("Create Project")
    }
}

@Composable
fun projectBody(onClickCreateProject: (title: String, description: String) -> Unit){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var project by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
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
        createProjectButton(onClickCreateProject = onClickCreateProject, title, description)
    }
}