package com.sofia.revio.controller

import com.sofia.revio.model.request.Token
import com.sofia.revio.model.request.UserBasic
import com.sofia.revio.service.AuthenticationService
import org.jboss.logging.Logger
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class AuthenticationController(
    private val authService: AuthenticationService
) {
    val logger: Logger = Logger.getLogger(this.javaClass.name)

    @PostMapping("/validate")
    fun validate(
        @RequestHeader("Authorization") authorization: String
    ): UserBasic {
        logger.info("AuthenticationController, validate:${authorization}")
        return authService.validate(authorization)
    }
}