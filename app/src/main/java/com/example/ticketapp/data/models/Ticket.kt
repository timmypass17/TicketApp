package com.example.ticketapp.data.models

data class Ticket(
    val title: String,
    val description: String,
    val project: String,
    var status: String,
    val priority: String,
    val createdAt: String,
    val author: String
){
    constructor() : this (
        title = "",
        description = "",
        project = "",
        status = "",
        priority = "",
        createdAt = "",
        author = ""
    )
}
