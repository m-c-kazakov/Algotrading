package com.finance.strategy.statistics.security.service

import com.finance.strategy.statistics.intagration.IntegrationTestBased
import com.finance.strategy.statistics.security.dto.UserDto
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class UserServiceImplTest : IntegrationTestBased() {
    @Autowired
    lateinit var userService: UserService



    @Test
    fun save() {

        val userName = "USER"
        val password = "USER"
        userService.save(UserDto(userName, password))

//        assertThat(userService.findUserByName(userName))
//            .isNotNull
//            .usingRecursiveComparison()
//            .ignoringFields("id")
//            .isEqualTo(User(userName, password))

    }

}