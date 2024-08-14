package com.example.crud.controllers

import com.example.crud.dto.UserLoginRequestDTO
import com.example.crud.dto.UserLoginResponseDTO
import com.example.crud.dto.UserRegisterReponseDTO
import com.example.crud.dto.UserRegisterRequestDTO
import com.example.crud.model.UserEntity
import com.example.crud.services.HashService
import com.example.crud.services.TokenService
import com.example.crud.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api")
class UserController(
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: UserLoginRequestDTO): UserLoginResponseDTO {
        val user = userService.findByName(payload.name)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Login Failed")

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Login Failed")
        }

        return UserLoginResponseDTO(token = tokenService.createToken(user))
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: UserRegisterRequestDTO): UserRegisterReponseDTO {
        if (userService.existByName(payload.name)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Register Failed")
        }

        val user = UserEntity(
            id = null,
            name = payload.name,
            password = hashService.hashBcrypt(payload.password)
        )

        val userSave = userService.save(user)

        return UserRegisterReponseDTO(id = userSave.id!!, name = userSave.name)
    }
}