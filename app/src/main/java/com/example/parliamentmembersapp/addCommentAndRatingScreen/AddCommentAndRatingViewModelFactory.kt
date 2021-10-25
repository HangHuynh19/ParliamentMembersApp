//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 6 October 2021

package com.example.parliamentmembersapp.addCommentAndRatingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembersapp.database.AppRepository
import com.example.parliamentmembersapp.database.CommentDao

// The purpose of this class is to instantiate the view model
class AddCommentAndRatingViewModelFactory(
private val dataSource: AppRepository) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddCommentAndRatingViewModel::class.java)) {
            return AddCommentAndRatingViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}