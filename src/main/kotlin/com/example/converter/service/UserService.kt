package com.example.converter.service

import com.example.converter.repos.UserRepo
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepo: UserRepo) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?) = userRepo.findByUsername(username)

}