//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 7 October 2021

package com.example.parliamentmembersapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RatingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rating: Rating)

    @Query("SELECT * FROM all_ratings WHERE rating_of_person_number == :key ORDER BY time_stamp DESC")
    fun getAllRating(key : Int) : LiveData<List<Rating>>

}