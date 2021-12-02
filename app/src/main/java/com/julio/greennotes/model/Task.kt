package com.julio.greennotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
data class Task (
                @PrimaryKey(autoGenerate = true)
                 val id: Int,
                 val name: String,
                 val description : String,
                 val assignetTo : String,
                 val dueDate: String,
                 val status : String) {
}
