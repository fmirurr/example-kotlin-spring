package com.example.crud.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserRegisterReponseDTO(
    @JsonProperty("id")
    var id: Long,
    @JsonProperty("name")
    var name: String,
)

data class UserLoginResponseDTO(
    @JsonProperty("token")
    var token: String
)