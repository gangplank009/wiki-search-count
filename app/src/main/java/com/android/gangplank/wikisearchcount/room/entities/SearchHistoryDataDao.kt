package com.android.gangplank.wikisearchcount.room.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.android.gangplank.wikisearchcount.room.SearchHistoryData

@Dao
interface SearchHistoryDataDao {

    @Query("SELECT * FROM searchHistoryData")
    fun getAll(): List<SearchHistoryData>

    @Insert(onConflict = REPLACE)
    fun addOne(searchHistoryData: SearchHistoryData)

    @Query("DELETE FROM searchHistoryData")
    fun deleteAll()
}