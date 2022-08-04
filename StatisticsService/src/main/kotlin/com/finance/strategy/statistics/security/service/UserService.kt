package com.finance.strategy.statistics.security.service

import com.finance.strategy.statistics.security.dto.UserDto
import com.finance.strategy.statistics.security.model.User
import java.util.*

interface UserService {

    fun save(userDto: UserDto)
    fun findUserByName(userName: String): Optional<User>
    fun findAll(): List<UserDto>
    fun saveAdminUser(userDto: UserDto)
}
