package com.example.parliamentmembersapp.database

import com.example.parliamentmembersapp.network.ParliamentApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// App repository - consolidate all the tables from Room
object AppRepository {
    val memberDao = AppDatabase.getInstance().memberDao
    val commentDao = AppDatabase.getInstance().commentDao
    val ratingDao = AppDatabase.getInstance().ratingDao

    suspend fun insertMember(memberData: List<MemberOfParliament>) = memberDao.insertAll(memberData)

    fun getAllMembers() = memberDao.getAllMembers()

    fun getAMember(personNumber : Int) = memberDao.getMember(personNumber)

    fun getAllComments(personNumber: Int) = commentDao.getAllComments(personNumber)

    fun getAllRatings(personNumber: Int) = ratingDao.getAllRating(personNumber)

    suspend fun refreshMembersData() {
        withContext(Dispatchers.IO) {
            val membersData = ParliamentApi.retrofitService.getParliamentMembers()
            memberDao.insertAll(membersData)
        }
    }
}