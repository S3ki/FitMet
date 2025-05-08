package com.example.fitmet.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "achievement",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Achievement(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val achieved: Boolean,
    val level: Int,
    val desc: String
)