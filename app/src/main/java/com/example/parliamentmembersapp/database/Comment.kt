//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 6 October 2021

package com.example.parliamentmembersapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_comments")
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val commentId: Long = 0L,
    @ColumnInfo(name = "owned_person_number")
    val ownedPersonNumber: Int,
    @ColumnInfo(name = "comments")
    val comments: String,
    @ColumnInfo(name = "time_stamp")
    val timestamp: Long
) {
    constructor(ownedPersonNumber: Int, comments: String, timestamp: Long) : this(
        0,
        ownedPersonNumber,
        comments,
        timestamp
    )
}

