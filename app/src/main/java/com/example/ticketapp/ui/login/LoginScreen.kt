package com.example.ticketapp.ui.login

import android.provider.SyncStateContract
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

@Composable
fun LoginScreen(
    onClickLogin: (email: String, pass: String) -> Unit,
    onClickDontHaveAnAccount: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Log In", fontSize = 30.sp)
        LoginEmail(email = email, onTextChange = { email = it })
        LoginPassword(pass = pass, onTextChange = { pass = it })
        LoginButton(onClickLogin = onClickLogin, email = email, pass = pass)
        DontHaveAnAccount(onClickDontHaveAnAccount = onClickDontHaveAnAccount)
    }
}

@Composable
fun LoginEmail(email: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = onTextChange,
        label = { Text("Enter email") }
    )
}

@Composable
fun LoginPassword(pass: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = pass,
        onValueChange = onTextChange,
        label = { Text("Enter password") }
    )
}

@Composable
fun LoginButton(onClickLogin: (email: String, pass: String) -> Unit, email: String, pass: String) {
    Button(onClick = { onClickLogin(email, pass) }, modifier = Modifier.padding(8.dp)) {
        Text(text = "Login")
    }
}

@Composable
fun DontHaveAnAccount(onClickDontHaveAnAccount: () -> Unit) {
    TextButton(onClick = { onClickDontHaveAnAccount() }) {
        Text("Don't have an account? Sign up!")
    }
}

@Composable
fun GoogleSignInButton(onClickSignIn: (String) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                onClickSignIn(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
            }
        })

    val context = LocalContext.current
    val token = stringResource(R.string.oauth_id)
    OutlinedButton(onClick = {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        launcher.launch(googleSignInClient.signInIntent)
    }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_google_logo),
            contentDescription = "Google sign in",
            tint = Color.Unspecified)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = "Login with Google",
            color = Color.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}