package com.sofia.revio.repository

import com.sofia.revio.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {

    fun findByEmail(email: String) : User?
}