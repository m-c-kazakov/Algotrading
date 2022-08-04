package com.finance.strategy.statistics.security.repository

import com.finance.strategy.statistics.security.model.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, Long>{

    fun findUserByUserName(userName: String) : Optional<User>
}