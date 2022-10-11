package com.sofia.revio.model

data class User(
    private val firstName: String,
    private val username: String,
    private val email: String,
    private val password: String,
    private val slackId: String,
    private val scope: Enum<ScopesEnum>
)