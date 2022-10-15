package com.sofia.revio.controller

import com.sofia.revio.model.request.UserBasic
import com.sofia.revio.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class AuthenticationController(
    private val authService: AuthenticationService
) {

    @PostMapping("/validate")
    fun validate(
        @RequestBody authorization: String
    ): UserBasic {
        return authService.validate(authorization)
    }
}