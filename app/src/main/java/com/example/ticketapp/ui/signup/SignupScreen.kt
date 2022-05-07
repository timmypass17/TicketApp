// Helo
package com.example.ticketapp.ui.signup

import android.inputmethodservice.Keyboard
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketapp.ui.dashboard.DashBoardBody
import com.example.ticketapp.ui.login.DontHaveAnAccount

@Composable
fun SignupScreen(
    onClickSignup: (name: String, email: String, pass: String, role: String) -> Unit,
    onClickBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { onClickBack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ){
        SignupBody(onClickSignup = onClickSignup)
    }
}


@Composable
fun SignupBody(
    onClickSignup: (name: String, email: String, pass: String, role: String) -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("Developer") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Signup", fontSize = 30.sp)
        SignupName(name = name, onTextChange = { name = it })
        SignupEmail(email = email, onTextChange = { email = it })
        SignupPass(pass = pass, onTextChange = { pass = it })

        // Radio button
        val radioOptions = listOf("Developer", "Program Manager")
        Log.i(TAG, "selected role: $selectedRole")
        // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
        Column(Modifier.selectableGroup().padding(start = 40.dp, end = 40.dp)) {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedRole),
                            onClick = { selectedRole = text },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedRole),
                        onClick = null // null recommended for accessibility with screenreaders
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1.merge(),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
        SignupButton(onClickSignup = onClickSignup, name, email, pass, selectedRole)
    }
}

@Composable
fun SignupName(name: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = onTextChange,
        label = { Text("Enter name") }
    )
}

@Composable
fun SignupEmail(email: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = onTextChange,
        label = { Text("Enter email") }
    )
}

@Composable
fun SignupPass(pass: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = pass,
        onValueChange = onTextChange,
        label = { Text("Enter password") }
    )
}

@Composable
fun SignupButton(
    onClickSignup: (name: String, email: String, pass: String, role: String) -> Unit,
    name: String,
    email: String,
    pass: String,
    role: String
) {
    Button(onClick = { onClickSignup(name, email, pass, role) }, modifier = Modifier.padding(8.dp)) {
        Text(text = "Signup")
    }
}