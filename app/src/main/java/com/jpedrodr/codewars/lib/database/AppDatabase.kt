package com.jpedrodr.codewars.lib.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jpedrodr.codewars.lib.database.dao.CompletedChallengeDao
import com.jpedrodr.codewars.lib.database.model.CompletedChallengeEntity
import com.jpedrodr.codewars.lib.database.model.converter.CompletedChallengeConverters

@Database(entities = [CompletedChallengeEntity::class], version = 1, exportSchema = false)
@TypeConverters(CompletedChallengeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun completedChallengeDao(): CompletedChallengeDao
}