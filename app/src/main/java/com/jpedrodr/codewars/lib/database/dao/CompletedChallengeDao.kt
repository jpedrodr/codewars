package com.jpedrodr.codewars.lib.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jpedrodr.codewars.lib.database.model.CompletedChallengeEntity

@Dao
interface CompletedChallengeDao {

    @Query("SELECT * FROM completed_challenges")
    fun getAll(): List<CompletedChallengeEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM completed_challenges LIMIT 1)")
    fun hasData(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(challenges: List<CompletedChallengeEntity>)

    @Query("DELETE FROM completed_challenges")
    fun deleteAll()

    @Transaction
    fun replaceAll(challenges: List<CompletedChallengeEntity>) {
        deleteAll()
        insertAll(challenges)
    }
}