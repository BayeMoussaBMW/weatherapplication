package com.kalamou.weatherapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kalamou.weatherapplication.model.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: Data)

    @Query("SELECT * from data WHERE id = :id")
    suspend fun getData(id: Int): Data

    @Query("SELECT * from data ORDER BY name ASC")
    fun getData(): Flow<List<Data>>

}
