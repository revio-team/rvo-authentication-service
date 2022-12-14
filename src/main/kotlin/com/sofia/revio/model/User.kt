package com.sofia.revio.model

import com.sofia.revio.validation.ValidEmail
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Id val id: String?,
    @field:NotNull("Name should not be null") val name: String,
    @field:ValidEmail
    @field:NotNull("E-mail should not be null") val email: String,
    @field:NotNull("username should not be null") val username: String,
    @field:NotNull("Password should not be null") var password: String,
    @field:NotNull("Scope should not be null") val scope: String?,
    val role: String = "dev",
    val active: Boolean? = null,
    val slackId: String?,
    val avatar: String?
)