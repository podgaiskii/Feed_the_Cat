package com.example.feedthecat.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity(tableName = "scores")
@TypeConverters(DateConverter::class)
data class Score(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "date")
    val date: Date = Date(System.currentTimeMillis()),

    @ColumnInfo(name = "score")
    val score: Int

)