//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 4 October 2021

package com.example.parliamentmembersapp.memberDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembersapp.database.AppRepository
import com.example.parliamentmembersapp.database.MemberOfParliamentDao

class MemberDetailViewModelFactory(
    private val personNum: Int,
    private val dataSource: AppRepository) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemberDetailViewModel::class.java)) {
            return MemberDetailViewModel(personNum, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}