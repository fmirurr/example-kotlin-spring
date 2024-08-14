package com.example.crud.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserRegisterRequestDTO(
    @JsonProperty("name")
    var name: String,
    @JsonProperty("password")
    var password: String,
)

data class UserLoginRequestDTO(
    @JsonProperty("name")
    var name: String,
    @JsonProperty("password")
    var password: String,
)