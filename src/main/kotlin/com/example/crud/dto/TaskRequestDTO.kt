package com.example.crud.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TaskRequestDTO (
    @JsonProperty("name")
    var name: String,
    @JsonProperty("description")
    var description: String,
    @JsonProperty("done")
    var done: Boolean
)