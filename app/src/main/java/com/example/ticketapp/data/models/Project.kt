package com.example.ticketapp.data.models

data class Project(
    val title: String,
    val description: String,
    val manager: User,
    val users: List<User>,
    val tickets: List<Ticket>,
    val createdAt: String
)