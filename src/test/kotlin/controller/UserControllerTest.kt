

package com.ecommerce.api.controller

import com.ecommerce.api.models.Address
import com.ecommerce.api.models.LoginDTO
import com.ecommerce.api.models.User
import com.ecommerce.api.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.put
import java.awt.PageAttributes


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest (
    @Autowired val mocMVC: MockMvc,
    @Autowired val objectMapper: ObjectMapper,
    @Autowired val userService: UserService

){

    @Test
    fun addUser() {
        var user = User("userTest@gmail.com", "prithvi", "7749850017", Address(47, "abc", "xyz", "od", "751025"))
        mocMVC.post("/User"){
            contentType = MediaType.APPLICATION_JSON
            content= objectMapper.writeValueAsString(user)
        }.andDo { print() }
            .andExpect { status{isOk()} }
        var userTemp = userService.getUserById(user.email)

        var loginDTOUser = LoginDTO(user.email,userTemp.password!!)
        mocMVC.put("/login"){
            contentType = MediaType.APPLICATION_JSON
            content= objectMapper.writeValueAsString(loginDTOUser)
        }.andDo { print() }
            .andExpect { status{isOk()} }


        mocMVC.post("/User"){
            contentType = MediaType.APPLICATION_JSON
            content= objectMapper.writeValueAsString(user)
        }
            .andExpect { status{isBadRequest()} }

        mocMVC.get("/User/${user.email}")
            .andDo { print() }
            .andExpect { status{isOk()} }

        mocMVC.get("/User")
            .andDo { println() }
            .andExpect {
                status{isOk()}
            }


    }
}
