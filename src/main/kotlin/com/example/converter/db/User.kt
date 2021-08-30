package com.example.converter.db

import lombok.NonNull
import javax.persistence.*


@Entity
@Table(name="USRS")
data class User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long=0L
                , val username: String
                , val password: String)
