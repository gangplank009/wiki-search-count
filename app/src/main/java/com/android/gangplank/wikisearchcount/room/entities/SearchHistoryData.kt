package com.android.gangplank.wikisearchcount.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchHistoryData")
data class SearchHistoryData(@PrimaryKey(autoGenerate = true) var id: Long?,
                             @ColumnInfo(name = "search_text") var searchText: String,
                             @ColumnInfo(name = "search_count") var count: Int
) {
    constructor(): this(null, "", 0)
}