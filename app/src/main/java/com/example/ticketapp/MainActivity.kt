package com.example.ticketapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ticketapp.ui.createProject.CreateProjectScreen
import com.example.ticketapp.ui.createProject.createProjectViewModel
import com.example.ticketapp.ui.createTicket.CreateTicketScreen
import com.example.ticketapp.ui.createTicket.createTicketViewModel
import com.example.ticketapp.ui.dashboard.DashBoardScreen
import com.example.ticketapp.ui.dashboard.DashBoardViewModel
import com.example.ticketapp.ui.login.LoginScreen
import com.example.ticketapp.ui.login.LoginViewModel
import com.example.ticketapp.ui.profile.ProfileScreen
import com.example.ticketapp.ui.signup.SignupScreen
import com.example.ticketapp.ui.signup.SignupViewModel
import com.example.ticketapp.ui.theme.TicketAppTheme
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginViewModel by viewModels<LoginViewModel>()
        val signupViewModel by viewModels<SignupViewModel>()
        val dashBoardViewModel by viewModels<DashBoardViewModel>()
        val createTicketViewModel by viewModels<createTicketViewModel>()
        val createProjectViewModel by viewModels<createProjectViewModel>()

        setContent {
            TicketApp(
                loginViewModel = loginViewModel,
                signupViewModel= signupViewModel,
                dashBoardViewModel = dashBoardViewModel,
                createTicketViewModel = createTicketViewModel,
                createProjectViewModel = createProjectViewModel
            )
        }
    }
}

@Composable
fun TicketApp(loginViewModel: LoginViewModel, signupViewModel: SignupViewModel, dashBoardViewModel: DashBoardViewModel, createTicketViewModel: createTicketViewModel, createProjectViewModel: createProjectViewModel) {
    TicketAppTheme() {
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
        val bottomNavScreens = listOf(TicketScreen.DashBoard, TicketScreen.Profile)
        val currentScreen = TicketScreen.fromRoute(
            backStackEntry.value?.destination?.route
        )
        Scaffold(
            bottomBar = {
                Log.i("MainActivity", (currentScreen != TicketScreen.Login).toString())

                // Show bottom bar if its not the login or signup screen
                if (currentScreen != TicketScreen.Login && currentScreen != TicketScreen.Signup) {
                    BottomBar(
                        allScreens = bottomNavScreens,
                        onTabSelected = { screen -> navController.navigate(screen.name) },
                        currentScreen = currentScreen
                    )
                }
            }
        ) { innerPadding ->
            TicketNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                loginViewModel = loginViewModel,
                signupViewModel = signupViewModel,
                dashBoardViewModel = dashBoardViewModel,
                createTicketViewModel = createTicketViewModel,
                createProjectViewModel = createProjectViewModel
            )
        }
    }
}

@Composable
fun TicketNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel,
    signupViewModel: SignupViewModel,
    dashBoardViewModel: DashBoardViewModel,
    createTicketViewModel: createTicketViewModel,
    createProjectViewModel: createProjectViewModel
) {
    NavHost(
        navController = navController,
        startDestination = if (loginViewModel.isLoggedIn) TicketScreen.DashBoard.name else TicketScreen.Login.name,
        modifier = modifier
    ) {
        composable(TicketScreen.Login.name) {
            LoginScreen(
                onClickLogin = loginViewModel::onClickLogin,
                onClickDontHaveAnAccount = {
                    navController.navigate(TicketScreen.Signup.name)
                }
            )
        }

        composable(TicketScreen.Signup.name) {
            SignupScreen(
                onClickSignup = signupViewModel::onClickSignUp,
                onClickBack = {
                    navController.navigate(TicketScreen.Login.name) {
                        popUpTo(TicketScreen.Login.name) { inclusive = true }
                    }
                }
            )
        }

        composable(TicketScreen.DashBoard.name) {
            dashBoardViewModel.getCurrentUser()
            DashBoardScreen(
                user = dashBoardViewModel.user,
                onClickAddProject = {
                    navController.navigate(TicketScreen.CreateProject.name)
                },
                onClickProject = { project ->

                }
            )
        }

        composable(TicketScreen.Profile.name) {
            ProfileScreen(
                user = dashBoardViewModel.user
            )
        }

        composable(TicketScreen.CreateTicket.name){
            CreateTicketScreen(onClickCreateTicket = createTicketViewModel::onClickCreateTicket)
        }

        composable(TicketScreen.CreateProject.name) {
            CreateProjectScreen(
                user = dashBoardViewModel.user,
                onClickCreateProject = createProjectViewModel::onClickCreateProject
            )
        }
    }
}

// Don't know why icons are invisible.. have to do it manually
@Composable
fun BottomBar(
    allScreens: List<TicketScreen>,
    onTabSelected: (TicketScreen) -> Unit,
    currentScreen: TicketScreen
) {
    BottomNavigation {
        allScreens.forEach() { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        screen.icon, contentDescription = null,
                        tint =  if (currentScreen == screen) Color.White else Color.White.copy(alpha = 0.60f)) },
                label = {
                    Text(
                        text = screen.name,
                        color = if (currentScreen == screen) Color.White else Color.White.copy(alpha = 0.60f)) },
                selected = currentScreen == screen,
                onClick = { onTabSelected(screen) }
            )
        }
    }
}