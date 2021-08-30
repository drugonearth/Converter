package com.example.converter.service

import com.example.converter.db.User
import com.example.converter.repos.UserRepo
import org.springframework.stereotype.Service


@Service
class UserService(private val db: UserRepo) {

    fun findUser(username: String?): User? = db.findByUsername(username)

    fun adduser(user: User)
    {
        db.save(user)
    }


}