package com.finance.strategy.statistics.security.service

import com.finance.strategy.statistics.security.model.UserDetailsImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userService: UserService) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {

        val user = userService.findUserByName(username)

        return user.map { UserDetailsImpl(it) }.orElseThrow { UsernameNotFoundException("Unknown user: $username") }
    }
}