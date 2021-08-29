package com.example.converter.controller

import lombok.*;
import com.example.converter.db.User
import com.example.converter.repos.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping


@Controller
class RegistrationController(@Autowired
                             private val userRepo: UserRepo) {

    @GetMapping("/registration")
    fun registration(): String {
        return "registration"
    }

    @PostMapping("/registration")
    fun addUser(user: User, model: MutableMap<String?, Any?>): String {
        val userFromDb: User = userRepo.findByUsername(user.username)
        if (userFromDb != null) {
            model["message"] = "User exists!"
            return "registration"
        }
        userRepo.save<User>(user)
        return "redirect:/login"
    }
}