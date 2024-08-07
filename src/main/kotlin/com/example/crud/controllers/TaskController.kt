package com.example.crud.controllers

import com.example.crud.dto.TaskRequestDTO
import com.example.crud.dto.TaskResponseDTO
import com.example.crud.model.TaskEntity
import com.example.crud.services.TaskService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/task")
class TaskController(var taskService: TaskService) {

    @GetMapping("/")
    fun allTask(): List<TaskEntity> {
        return taskService.getAllTask()
    }

    @PostMapping("/create")
    fun createTask(@RequestBody newTask: TaskRequestDTO): TaskResponseDTO {
        return taskService.createTask(newTask)
    }

    @GetMapping("/{id}")
    fun getTask(@PathVariable id: Long): TaskResponseDTO {
        return taskService.getTask(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found")
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long) {
        taskService.deleteTask(id)
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody updateTask: TaskRequestDTO): TaskResponseDTO {
        return taskService.updateTask(id, updateTask)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found")
    }
}