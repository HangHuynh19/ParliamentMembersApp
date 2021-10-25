//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 2 October 2021

package com.example.parliamentmembersapp.allMembersScreen

import androidx.lifecycle.*
import com.example.parliamentmembersapp.database.AppRepository
import com.example.parliamentmembersapp.database.MemberOfParliament

class AllMembersViewModel(dataSource: AppRepository) : ViewModel() {

    val database = dataSource

    private var _members : LiveData<List<MemberOfParliament>>
    val members
        get() = _members

    private val _navigateToMemberDetail = MutableLiveData<Int?>()
    val navigateToMemberDetail
        get() = _navigateToMemberDetail

    fun onMemberClicked(id: Int) {
        _navigateToMemberDetail.value = id
    }

    fun onMemberDetailNavigated() {
        _navigateToMemberDetail.value = null
    }

    init {
        _members = database.getAllMembers()
    }
}