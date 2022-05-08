package com.example.ticketapp.data.models

data class Ticket(
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val createdAt: String
)