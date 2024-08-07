package com.example.crud.repositories

import com.example.crud.model.TaskEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<TaskEntity, Long> {
}