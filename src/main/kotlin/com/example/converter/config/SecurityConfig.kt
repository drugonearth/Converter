package com.example.converter.config

import com.example.converter.service.UserService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.web.savedrequest.NullRequestCache

@Configuration
@EnableWebSecurity
class SecurityConfig(private val  userService: UserService) : WebSecurityConfigurerAdapter() {


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
                .antMatchers("/registration", "/resources/static/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .requestCache().requestCache(NullRequestCache()).
            and().
            csrf().disable()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll()

    }

    @Throws(java.lang.Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService)
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
    }

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers("/resources/**", "/static/**", "/css/**", "/registration/**","/js/**")
    }

}


