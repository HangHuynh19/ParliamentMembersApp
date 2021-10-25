//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 1 October 2021

package com.example.parliamentmembersapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemberOfParliamentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(members: List<MemberOfParliament>)

    @Update
    suspend fun update(member: MemberOfParliament)

    @Query("SELECT * FROM members_of_parliament_table WHERE personNumber == :key")
    fun getMember(key: Int) : LiveData<MemberOfParliament>

    @Query("SELECT * FROM members_of_parliament_table ORDER BY first_name ASC")
    fun getAllMembers() : LiveData<List<MemberOfParliament>>

    @Query("DELETE FROM members_of_parliament_table")
    suspend fun clear()

}