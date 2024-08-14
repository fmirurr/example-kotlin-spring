package com.example.crud.services

import com.example.crud.model.UserEntity
import com.example.crud.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findById(id: Long): UserEntity? {
        return userRepository.findByIdOrNull(id)
    }

    fun findByName(name: String): UserEntity? {
        return userRepository.findByName(name)
    }

    fun existByName(name: String): Boolean {
        return userRepository.existsByName(name)
    }

    fun save(user: UserEntity): UserEntity {
        return userRepository.save(user)
    }
}