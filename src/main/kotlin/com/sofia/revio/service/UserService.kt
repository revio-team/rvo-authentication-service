package com.sofia.revio.service

import com.sofia.revio.model.User
import com.sofia.revio.model.request.UserRequest
import com.sofia.revio.model.request.toUser
import com.sofia.revio.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import kotlin.Exception

@Service
class UserService(
    private val userRepository: UserRepository,
    private val bCrypt: BCryptPasswordEncoder = BCryptPasswordEncoder()
) {

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

    fun login(email: String, password: String): Boolean {
        val user = userRepository.findByEmail(email) ?: throw Exception("User not existent")

        if (!bCrypt.matches(password, user.password)) {
            return false
        }
        return true
    }
}