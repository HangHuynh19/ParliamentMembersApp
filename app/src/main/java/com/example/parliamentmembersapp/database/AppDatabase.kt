package com.example.parliamentmembersapp.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parliamentmembersapp.MyApp

@Database(entities = [MemberOfParliament::class, Comment::class, Rating::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val memberDao : MemberOfParliamentDao
    abstract val commentDao : CommentDao
    abstract val ratingDao : RatingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        MyApp.appContext,
                        AppDatabase::class.java,
                        "member_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}