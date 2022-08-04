package com.finance.strategy.statistics.security.service

import com.finance.strategy.statistics.security.dto.UserDto
import com.finance.strategy.statistics.security.model.Role
import com.finance.strategy.statistics.security.model.User
import com.finance.strategy.statistics.security.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun save(userDto: UserDto) {

        val optionalUser = userRepository.findUserByUserName(userDto.username)

        if (optionalUser.isEmpty) {
            val user = User(userName = userDto.username, password = passwordEncoder.encode(userDto.password))
            userRepository.save(user)
        } else {
            throw RuntimeException("Пользователь с таким именем=${userDto.username} уже существует")
        }
    }

    override fun findUserByName(userName: String): Optional<User> {
        return userRepository
            .findUserByUserName(userName)
    }

    override fun findAll(): List<UserDto> {
        return userRepository
            .findAll()
            .map {
                UserDto(it.userName, it.password)
            }
    }

    override fun saveAdminUser(userDto: UserDto) {
        val optionalUser = userRepository.findUserByUserName(userDto.username)

        if (optionalUser.isEmpty) {
            val user = User(
                userName = userDto.username,
                password = passwordEncoder.encode(userDto.password),
                roles = mutableListOf(Role.ADMIN)
            )
            userRepository.save(user)
        } else {
            throw RuntimeException("Админ с таким именем=${userDto.username} уже существует")
        }
    }
}