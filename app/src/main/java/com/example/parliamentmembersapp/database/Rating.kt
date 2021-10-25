//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 7 October 2021

package com.example.parliamentmembersapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_ratings")
data class Rating(
    @PrimaryKey(autoGenerate = true)
    val ratingId: Long = 0L,
    @ColumnInfo(name = "rating_of_person_number")
    val ratingOfPersonNumber: Int,
    @ColumnInfo(name = "rating")
    val rating: Float,
    @ColumnInfo(name = "time_stamp")
    val timestamp: Long
) {
    constructor(ratingOfPersonNumber: Int, rating: Float, timestamp: Long) : this(
        0,
        ratingOfPersonNumber,
        rating,
        timestamp
    )
}