package com.example.converter.db

import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
@NoArgsConstructor
data class User(@Id @GeneratedValue(strategy = GenerationType.AUTO)
                val id: Long, val username: String, val password: String) {
}