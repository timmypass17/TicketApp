package com.example.ticketapp.data.models

data class Ticket(
    val title: String,
    val description: String,
    val project: String,
    val status: String,
    val priority: String,
    val createdAt: String
){
//    constructor() : this (
//        title = "",
//        description = "",
//        project = "",
//        status = "",
//        priority = "",
//        createdAt = ""
//    )
}
