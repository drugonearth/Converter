package com.example.converter.db


import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
@Table(name="USRS")
data class User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long=0L
                , private val username: String
                , private val password: String
                , var active: Boolean = false
                , @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
                @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
                @Enumerated(EnumType.STRING)
                var roles: Set<Role>? = null ) : UserDetails
{
    override fun getAuthorities(): Set<Role>? = roles

    override fun getPassword() = password

    override fun getUsername() = username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

}

