package com.android.gangplank.wikisearchcount.room.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.gangplank.wikisearchcount.room.SearchHistoryData

@Database(entities = arrayOf(SearchHistoryData::class), version = 1)
abstract class SearchHistoryDataBase: RoomDatabase() {
    abstract fun searchHistoryDataDao(): SearchHistoryDataDao

    companion object {
        private var INSTANCE: SearchHistoryDataBase? = null

        fun getInstance(context: Context): SearchHistoryDataBase {
            if (INSTANCE == null)
                synchronized(SearchHistoryDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SearchHistoryDataBase::class.java, "search_history_db")
                        .allowMainThreadQueries()
                        .build()
                }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}