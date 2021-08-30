package com.example.converter.db

import org.springframework.security.core.GrantedAuthority


enum class Role : GrantedAuthority {
    USER;

    override fun getAuthority() = name
}