package com.example.ticketapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class TicketScreen(
    val icon: ImageVector
) {
    Login(Icons.Default.Face),
    Signup(Icons.Default.Face),
    DashBoard(Icons.Default.Home),
    Profile(Icons.Default.Person),
    ProjectDetail(Icons.Default.Face),
    CreateTicket(Icons.Default.Face),
    CreateProject(Icons.Default.Face),
    TicketDetails(Icons.Default.Face);

    companion object {
        fun fromRoute(route: String?): TicketScreen =
            when (route?.substringBefore("/")) {
                Login.name -> Login
                Signup.name -> Signup
                DashBoard.name -> DashBoard
                Profile.name -> Profile
                ProjectDetail.name -> ProjectDetail
                CreateTicket.name -> CreateTicket
                CreateProject.name -> CreateProject
                TicketDetails.name -> TicketDetails
                null -> Login
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}