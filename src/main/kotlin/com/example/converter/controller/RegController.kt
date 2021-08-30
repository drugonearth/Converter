package com.example.converter.controller

import com.example.converter.db.Role
import com.example.converter.db.User
import com.example.converter.repos.UserRepo
import com.example.converter.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.util.*


@Controller
class RegistrationController(@Autowired private val userRepo: UserRepo) {

    @GetMapping("/registration")
    fun registration(): String {
        return "registration"
    }

    @PostMapping("/registration")
    fun addUser(user: User, model: MutableMap<String, Any>): String {
        val userFromDb: User? = userRepo.findByUsername(user.username)

        if (userFromDb != null) {
            model["message"] = "User exists"
            return "registration"
        }

        user.active = true
        user.roles = (Collections.singleton(Role.USER))
        userRepo.save(user)

        return "redirect:/login"
    }
}