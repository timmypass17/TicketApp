package com.example.ticketapp.data.models

data class User(
    val id: String,
    val name: String,
    val email: String,
    val pass: String,
    val role: String
) {
    constructor() : this (
        id = "",
        name = "",
        email = "",
        pass = "",
        role = ""
    )
}