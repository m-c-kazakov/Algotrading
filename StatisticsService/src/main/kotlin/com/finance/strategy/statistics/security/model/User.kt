package com.finance.strategy.statistics.security.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id
    var id: Long? = null,
    val userName: String,
    val password: String,
    val roles: MutableCollection<Role> = mutableListOf(Role.USER),
    var enabled: Boolean = true
) {

}
