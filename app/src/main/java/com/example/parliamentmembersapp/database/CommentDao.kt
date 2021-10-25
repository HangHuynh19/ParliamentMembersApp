//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 6 October 2021

package com.example.parliamentmembersapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comment: Comment)

    @Query("SELECT * FROM all_comments WHERE owned_person_number == :key ORDER BY time_stamp DESC")
    fun getAllComments(key : Int) : LiveData<List<Comment>>
}