package com.kalamou.weatherapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kalamou.weatherapplication.db.model.ItemName
import com.kalamou.weatherapplication.model.Data

@Database(entities = [ItemName::class, Data::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemNameDao(): ItemNameDao
    abstract fun dataDao(): DataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}