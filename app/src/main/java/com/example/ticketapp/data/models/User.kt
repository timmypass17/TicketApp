package com.example.ticketapp.data.models

data class User(
    val id: String,
    val name: String,
    val email: String,
    val pass: String,
    val role: String,
    val projects: List<Project>
) {
    constructor() : this (
        id = "",
        name = "",
        email = "",
        pass = "",
        role = "",
        projects = listOf()
    )
}