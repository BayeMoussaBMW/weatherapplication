package com.kalamou.weatherapplication.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.kalamou.weatherapplication.model.Data

@Entity(tableName = "item")
data class ItemName(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val name: String
)