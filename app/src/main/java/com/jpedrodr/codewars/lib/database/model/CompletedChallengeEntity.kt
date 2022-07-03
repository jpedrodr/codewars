package com.jpedrodr.codewars.lib.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_challenges")
data class CompletedChallengeEntity(
    @PrimaryKey val id: String,
    val name: String,
    val slug: String,
    @ColumnInfo(name = "completed_at") val completedAt: String,
    @ColumnInfo(name = "completed_languages") val completedLanguages: List<String>
)