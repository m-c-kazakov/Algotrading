package com.finance.strategy.statistics.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.finance.strategy.statistics.intagration.IntegrationTestBased
import com.finance.strategy.statistics.security.dto.UserDto
import com.finance.strategy.statistics.security.service.UserService
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.ContentType
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.mock.web.MockHttpSession
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.servlet.function.ServerResponse.status

private const val ADMIN = "admin"

private const val USER = "user"

@AutoConfigureMockMvc
class WebSecurityConfigTest : IntegrationTestBased() {


    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var userService: UserService


    @BeforeEach
    fun beforeEach() {
        userService
            .findUserByName(ADMIN)
            .ifPresentOrElse({ }) {
                userService.saveAdminUser(UserDto(ADMIN, ADMIN))
            }
        userService
            .findUserByName(USER)
            .ifPresentOrElse({ }) {
                userService.save(UserDto(USER, USER))
            }
    }

    @Test
    fun `create page is not protected and user will created`() {

        val objectMapper = ObjectMapper()
        val username = "ALESHA"
        val password = "password"
        val userDto = objectMapper.writeValueAsString(UserDto(username, password))

        mvc
            .perform(
                post("/api/v1/users")
                    .contentType(ContentType.APPLICATION_JSON.mimeType)
                    .content(userDto)
            )
            .andExpect(SecurityMockMvcResultMatchers.unauthenticated())
            .andExpect {
                status(HttpStatus.SC_OK)
            }

        assertThat(userService.findUserByName(username)).isPresent
    }

    @Test
    fun `login page is protected and user is authenticated after login`() {

        mvc
            .perform(
                SecurityMockMvcRequestBuilders
                    .formLogin("/login")
                    .user(USER)
                    .password(USER)
            )
            .andExpect {
                status(HttpStatus.SC_OK)
            }
            .andExpect(SecurityMockMvcResultMatchers.authenticated())

    }

    @Test
    fun `protected page redirects to login`() {
        mvc
            .get("/api/v1/statistics")
            .andExpect {
                status { is3xxRedirection() }
                redirectedUrlPattern("**/login")
            }
    }


    @Test
    fun `invalid user is not authenticated after login`() {
        this.mvc
            .perform(
                SecurityMockMvcRequestBuilders
                    .formLogin("/login")
                    .user("invalid")
                    .password("invalid")
            )
            .andExpect(SecurityMockMvcResultMatchers.unauthenticated())
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection)
    }

    @Test
    fun `admin can access getAllUsers resource`() {


        val httpSession = getMockHttpSession(ADMIN, ADMIN)

        mvc
            .get("/api/v1/users") { session = httpSession }
            .andExpect {
                status { isOk() }
            }
    }

    @Test
    fun `user can access findAllStatistics resource`() {


        val httpSession = getMockHttpSession(USER, USER)

        mvc
            .get("/api/v1/statistics") { session = httpSession }
            .andExpect {
                status { isOk() }
            }
    }

    @Test
    fun `user cannot access getAllUsers resource`() {
        val httpSession = getMockHttpSession(USER, USER)

        mvc
            .get("/api/v1/users") { session = httpSession }
            .andExpect {
                status { isForbidden() }
            }
    }

    private fun getMockHttpSession(username: String, password: String) = mvc
        .perform(
            SecurityMockMvcRequestBuilders
                .formLogin("/login")
                .user(username)
                .password(password)
        )
        .andExpect {
            status(HttpStatus.SC_OK)
        }
        .andExpect(SecurityMockMvcResultMatchers.authenticated())
        .andReturn().request.getSession(false) as MockHttpSession
}