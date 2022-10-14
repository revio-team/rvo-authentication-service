package com.sofia.revio.model.request

import com.sofia.revio.model.User

data class UserRequest(
    val name: String, val email: String, val username: String, var password: String, val scope: String
)

fun UserRequest.toUser(): User {
    return User(
        null, this.name, this.email, this.username, this.password, this.scope, true, null, null
    )
}
