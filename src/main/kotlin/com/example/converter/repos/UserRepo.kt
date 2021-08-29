package com.example.converter.repos

import com.example.converter.db.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo : JpaRepository<User, Long> {
    fun findByUsername(username: String): User
}