package com.kalamou.weatherapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kalamou.weatherapplication.db.model.ItemName
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemNameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(itemName: ItemName)

    @Query("SELECT * from item WHERE id = :id")
    suspend fun getItem(id: Int): ItemName

    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<ItemName>>
}