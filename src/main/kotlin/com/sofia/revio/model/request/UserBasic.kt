package com.sofia.revio.model.request

data class UserBasic(
    val id: String,
    val username: String,
    val name: String,
    val email: String,
    val role: String
)