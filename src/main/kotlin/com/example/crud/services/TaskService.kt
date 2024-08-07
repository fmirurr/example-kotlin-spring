package com.example.crud.services

import com.example.crud.dto.TaskRequestDTO
import com.example.crud.dto.TaskResponseDTO
import com.example.crud.model.TaskEntity
import com.example.crud.repositories.TaskRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class TaskService(var repository: TaskRepository) {
    fun createTask(newTask: TaskRequestDTO): TaskResponseDTO {
        val save = repository.save(
            TaskEntity(
                id = null,
                name = newTask.name,
                description = newTask.description,
                done = newTask.done
            )
        )
        return TaskResponseDTO(id = save.id!!, name = save.name, description = save.description, done = save.done)
    }

    fun getAllTask(): List<TaskEntity> {
        return repository.findAll().toList()
    }

    fun getTask(id: Long): TaskResponseDTO? {
        return repository.findById(id)
            .map { TaskResponseDTO(id = it.id!!, name = it.name, description = it.description, done = it.done) }
            .getOrNull()
    }

    fun deleteTask(id:Long) {
        repository.deleteById(id)
    }

    fun updateTask(id: Long, updateTask: TaskRequestDTO): TaskResponseDTO? {
        return repository.findById(id).map {
            val save = repository.save(
                TaskEntity(
                    id = it.id,
                    name = updateTask.name,
                    description = updateTask.description,
                    done = updateTask.done
                )
            )
            TaskResponseDTO(id = save.id!!, name = save.name, description = save.description, done = save.done)
        }.orElseGet(null)
    }
}