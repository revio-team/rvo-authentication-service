package com.sofia.revio.service

import com.sofia.revio.model.User
import com.sofia.revio.model.request.UserRequest
import com.sofia.revio.model.request.toUser
import com.sofia.revio.repository.UserRepository
import org.jboss.logging.Logger
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import kotlin.Exception
import kotlin.math.log

@Service
class UserService(
    private val userRepository: UserRepository,
    private val bCrypt: BCryptPasswordEncoder = BCryptPasswordEncoder(),
    private val jwtTokenService: JwtTokenService
) {
    val logger: Logger = Logger.getLogger(this.javaClass.name)
    lateinit var user: User

    fun getAll(): MutableList<User> {
        return userRepository.findAll()
    }


    fun save(userRequest: UserRequest): User {
        if (emailExist(userRequest.email)) {
            throw Exception("Email already exists.")
        }
        userRequest.password = bCrypt.encode(userRequest.password)
        val user = userRequest.toUser()
        return userRepository.save(user)
    }

    fun emailExist(email: String): Boolean {
        return userRepository.findByEmail(email) != null
    }

    fun login(email: String, password: String): String {
        val user = userRepository.findByEmail(email) ?: throw Exception("User not existent")

        if (!bCrypt.matches(password, user.password)) {
            throw Exception("E-mail or password incorrect.")
        }
        return generateToken(user)
    }

    fun generateToken(user: User): String {
        return jwtTokenService.generateToken(user)
    }

    fun validateToken(requestToken: String): UserService {

        val isValid = requestToken.contains(BEARER_STRING)
        println("UserService, validateToken: $requestToken")

        if (isValid.not()) {
            println("UserService, token is not valid.")
            throw Exception("Invalid token.")
        }

        println("UserService, sanitizedToken")
        val sanitizedToken = requestToken.removePrefix(BEARER_STRING)

        println("UserService, getUsernameFromToken")
        val username = jwtTokenService.getUsernameFromToken(sanitizedToken)

        println("UserService, findByUsername")
        this.user = userRepository.findByUsername(username) ?: throw Exception("Invalid token.")

        println("UserService, validateTokenSanitizedToken: $sanitizedToken")
        if (jwtTokenService.validateToken(sanitizedToken, user)) {
            return this
        }
        throw Exception("Invalid token")
    }

    companion object {
        const val BEARER_STRING = "Bearer "
    }
}