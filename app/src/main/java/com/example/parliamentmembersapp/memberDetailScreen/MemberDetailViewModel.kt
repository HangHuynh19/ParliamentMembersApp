//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 4 October 2021

package com.example.parliamentmembersapp.memberDetailScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembersapp.database.*
import kotlinx.coroutines.launch

class MemberDetailViewModel(personNumber: Int, dataSource: AppRepository) : ViewModel() {
    val database = dataSource

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var _member : LiveData<MemberOfParliament>
    val member
        get() = _member

    private var _comments : LiveData<List<Comment>>
    val comments
        get() = _comments

    private var _ratings : LiveData<List<Rating>>
    val ratings
        get() = _ratings

    private val _navigateToAddCommentAndRating = MutableLiveData<Int?>()
    val navigateToAddCommentAndRating
        get() = _navigateToAddCommentAndRating

    fun onAddCommentAndRatingNavigated() {
        _navigateToAddCommentAndRating.value = null
    }

    init {
        _member = getMember(personNumber)
        _comments = getComments(personNumber)
        _ratings = getRatings(personNumber)
    }

    // Get member according to their personal number
    private fun getMember(personNumber: Int): LiveData<MemberOfParliament> {
        viewModelScope.launch {
            try {
                _member = database.getAMember(personNumber)
                _response.value = "Success: Parliament members retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
        return _member
    }

    // Get comments of a member using the personal number
    private fun getComments(personNumber: Int): LiveData<List<Comment>> {
        viewModelScope.launch {
            try {
                _comments = database.getAllComments(personNumber)
                _response.value = "Success: Comments retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
        return _comments
    }

    // Get rating of a member using the personal number
    private fun getRatings(personNumber: Int) : LiveData<List<Rating>> {
        try {
            _ratings = database.getAllRatings(personNumber)
            _response.value = "Success: Rating list retrieved"
        } catch (e: Exception) {
            _response.value = "Failure: ${e.message}"
        }
        return _ratings
    }
}