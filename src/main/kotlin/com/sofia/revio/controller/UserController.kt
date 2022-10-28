package com.sofia.revio.controller

import com.sofia.revio.model.User
import com.sofia.revio.model.request.UserLogin
import com.sofia.revio.model.request.UserRequest
import com.sofia.revio.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/v1/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getUsers(
        @RequestHeader("Authorization") requestToken: String
    ): MutableList<User> {
        return userService.validateToken(requestToken).getAll()
    }

    @PostMapping
    fun registerUser(
        @Valid @RequestBody userRequest: UserRequest
    ): ResponseEntity<User> {
        val user = userService.save(userRequest)
        val token = userService.generateToken(user)

        val responseHeaders = HttpHeaders()
        responseHeaders.set("Authorization", token)

        return ResponseEntity(responseHeaders, HttpStatus.CREATED)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody userLogin: UserLogin
    ): ResponseEntity<Any> {
        val authentication = userService.login(userLogin.email, userLogin.password)

        val responseHeaders = HttpHeaders()
        responseHeaders.set("Authorization", authentication)

        return ResponseEntity(responseHeaders, HttpStatus.OK)
    }

}