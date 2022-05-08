package com.example.ticketapp.data.models

data class Project(
    val title: String,
    val description: String,
    val manager: User,
    val users: List<User>,
    val tickets: List<Ticket>,
    val createdAt: String,
    val due: String
) {
    constructor() : this (
        title = "",
        description = "",
        manager = User(),
        users = listOf(),
        tickets = listOf(),
        createdAt = "",
        due = ""
    )
}