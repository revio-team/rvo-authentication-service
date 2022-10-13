package com.sofia.revio.model.request

data class UserRequest(
    val name: String,
    val email: String,
    val password: String,
    val scope: String
)