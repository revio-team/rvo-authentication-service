package com.sofia.revio.controller

import com.sofia.revio.model.User
import com.sofia.revio.service.UserService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/v1/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getUsers(): MutableList<User> {
        return userService.getAll()
    }

    @PostMapping
    fun registerUser(
        @Valid @RequestBody user: User
    ): User {
        return userService.save(user)
    }
}