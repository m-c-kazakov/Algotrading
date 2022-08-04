package com.finance.strategy.statistics.security.controller

import com.finance.strategy.statistics.security.dto.UserDto
import com.finance.strategy.statistics.security.service.UserService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
class SecurityController(
    private val userService: UserService
) {

    @PostMapping("/api/v1/users")
    fun createUser(@RequestBody userDto: UserDto) {
        userService.save(userDto)
    }

    @GetMapping("/api/v1/users")
    fun getUsers(): List<UserDto> {
        return userService.findAll()
    }
}