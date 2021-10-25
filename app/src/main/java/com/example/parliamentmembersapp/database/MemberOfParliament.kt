//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 1 October 2021

package com.example.parliamentmembersapp.database

import androidx.room.*

@Entity(tableName = "members_of_parliament_table")
data class MemberOfParliament(
    @PrimaryKey(autoGenerate = false)
    val personNumber: Int,
    @ColumnInfo(name = "seat_number")
    val seatNumber: Int = 0,
    @ColumnInfo(name = "last_name")
    var last: String,
    @ColumnInfo(name = "first_name")
    var first: String,
    @ColumnInfo(name = "party")
    val party: String,
    @ColumnInfo(name = "is_a_minister")
    val minister: Boolean = false,
    @ColumnInfo(name = "picture")
    val picture: String = "",
    @ColumnInfo(name = "twitter_link")
    val twitter: String = "",
    @ColumnInfo(name = "born_year")
    var bornYear: Int = 0,
    @ColumnInfo(name = "constituency")
    var constituency: String = ""
)


