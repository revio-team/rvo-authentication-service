package com.sofia.revio.service

import com.sofia.revio.model.User
import com.sofia.revio.repository.UserRepository
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getAll(): MutableList<User> {
        return userRepository.findAll()
    }

    fun save(user: User): User {
        if(emailExist(user.email)){
            throw Exception("Email already exists.")
        }
        return userRepository.save(user)
    }

    fun emailExist(email: String): Boolean {
        return userRepository.findByEmail(email) != null
    }
}