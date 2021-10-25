//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 6 October 2021

package com.example.parliamentmembersapp.addCommentAndRatingScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembersapp.database.AppRepository
import com.example.parliamentmembersapp.database.Comment
import com.example.parliamentmembersapp.database.Rating
import kotlinx.coroutines.launch

class AddCommentAndRatingViewModel(dataSource: AppRepository) :
    ViewModel() {
    val database = dataSource

    private val _navigateToMemberDetailFragment = MutableLiveData<Boolean?>()
    val navigateToMemberDetailFragment: LiveData<Boolean?>
        get() = _navigateToMemberDetailFragment

    fun doneNavigating() {
        _navigateToMemberDetailFragment.value = null
    }

    // This function handles the event when Done button is clicked.
    // When the done button is clicked, the following things occur:
    // 1. Instances of Comment and Rating are created
    // 2. If the comment is not empty, it is added to the database
    // 3. Similarly, if the rating is greater than 0.0, it is also added to the database
    // 4. Set the variable _navigateToMemberDetailFragment to true
    fun onDoneButtonClicked(personNumber: Int, comment: String, rating: Float, timestamp: Long) {

        viewModelScope.launch {
            if (rating > 0.0) {
                database.ratingDao.insert(Rating(personNumber, rating, timestamp))
            }

            if (comment != "") {
                database.commentDao.insert(Comment(
                    personNumber,
                    comment,
                    timestamp
                ))
            }

        }
    }
}