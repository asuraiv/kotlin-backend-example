package com.asuraiv.kotlinbackend.example.domain.user

import com.asuraiv.kotlinbackend.example.config.DataSourceConfig
import com.asuraiv.kotlinbackend.example.domain.user.dto.UserCreateRequest
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.Test
import kotlin.test.assertNotNull

@RunWith(SpringRunner::class)
@SpringBootTest(
    properties = ["spring.config.location=classpath:application-test.yml"],
    classes = [UserService::class, DataSourceConfig::class, HibernateJpaAutoConfiguration::class])
class UserServiceTest {

    @Autowired
    lateinit var userService: UserService

    @Test
    fun `사용자 생성, 조회 테스트`() {

        val username = "test"

        val createRequest = UserCreateRequest(userName = username, password = "1234")
        userService.createPerson(request = createRequest)

        val foundOne = userService.getUser(username = username)

        assertNotNull(actual = foundOne)
    }

    @Test
    fun `사용자 삭제 테스트`() {


    }
}