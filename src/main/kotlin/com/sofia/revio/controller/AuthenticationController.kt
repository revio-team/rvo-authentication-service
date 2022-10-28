package com.sofia.revio.controller

import com.sofia.revio.model.request.Token
import com.sofia.revio.model.request.UserBasic
import com.sofia.revio.service.AuthenticationService
import org.jboss.logging.Logger
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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
        @RequestBody token: Token
    ): UserBasic {
        logger.info("AuthenticationController, validate:${token.authorization}")
        return authService.validate(token.authorization)
    }
}