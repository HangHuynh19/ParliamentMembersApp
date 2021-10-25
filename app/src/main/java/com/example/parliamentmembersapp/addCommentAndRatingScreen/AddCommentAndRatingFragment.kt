//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 6 October 2021

package com.example.parliamentmembersapp.addCommentAndRatingScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.parliamentmembersapp.R
import com.example.parliamentmembersapp.database.AppRepository
import com.example.parliamentmembersapp.databinding.FragmentAddCommentAndRatingBinding
import com.example.parliamentmembersapp.memberDetailScreen.MemberDetailFragmentArgs
import java.time.LocalDateTime
import java.time.ZoneOffset

class AddCommentAndRatingFragment : Fragment() {

    @SuppressLint("NewApi", "ServiceCast")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentAddCommentAndRatingBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_comment_and_rating, container, false
        )

        val arguments = arguments?.let {
            MemberDetailFragmentArgs.fromBundle(it)
        }

        // Create an instance of the ViewModel Factory.
        val dataSource = AppRepository
        val viewModelFactory =
            arguments?.personNum?.let { AddCommentAndRatingViewModelFactory(dataSource) }

        // Get a reference to the ViewModel associated with this fragment.
        val addCommentAndRatingViewModel =
            viewModelFactory?.let {
                ViewModelProvider(
                    this,
                    it
                ).get(AddCommentAndRatingViewModel::class.java)
            }

        // Give the binding object the preference to the view model
        binding.commentAndRatingViewModel = addCommentAndRatingViewModel

        // Set the lifeCycleOwner to this fragment
        binding.lifecycleOwner = this

        // Create the onClickListener for the Done button.
        // When the Done button is clicked, the following things occur:
        // 1. The user is navigated back to the Member Detail screen
        // 2. Comment and rating is added to Room database
        // 3. Set the variable _navigateToMemberDetailFragment value to null
        binding.doneButton.setOnClickListener {

            if (arguments != null) {
                addCommentAndRatingViewModel?.apply {
                    doneNavigating()
                    onDoneButtonClicked(
                        arguments.personNum,
                        binding.addCommentEditText.text.toString(),
                        binding.ratingBar.rating,
                        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
                    )

                    Toast.makeText(context, "Submitted", Toast.LENGTH_SHORT).show()

                    binding.addCommentEditText.text = null
                    binding.ratingBar.rating = 0F

                    val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
                }
            }
        }

        return binding.root
    }
}