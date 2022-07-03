package com.jpedrodr.codewars.lib.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jpedrodr.codewars.lib.database.model.CompletedChallengeEntity

@Dao
interface CompletedChallengeDao {

    @Query("SELECT * FROM completed_challenges")
    fun getAll(): List<CompletedChallengeEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM completed_challenges LIMIT 1)")
    fun hasData(): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(challenges: List<CompletedChallengeEntity>)
}