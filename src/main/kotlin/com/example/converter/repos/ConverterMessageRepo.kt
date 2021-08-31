package com.example.converter.repos

import com.example.converter.db.ConverterMessage
import com.example.converter.db.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ConverterMessageRepo: JpaRepository<ConverterMessage, Long> {
    fun findByUser(user: User): List<ConverterMessage?>?
}