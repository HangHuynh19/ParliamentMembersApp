//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 3 October 2021

package com.example.parliamentmembersapp.memberDetailScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.parliamentmembersapp.*
import com.example.parliamentmembersapp.database.AppRepository
import com.example.parliamentmembersapp.databinding.FragmentMemberDetailBinding
import timber.log.Timber

class MemberDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding = DataBindingUtil.inflate<FragmentMemberDetailBinding>(
            inflater,
            R.layout.fragment_member_detail,
            container,
            false
        )

        // Get the arguments passed from AllMembersFragment
        val arguments = arguments?.let {
            MemberDetailFragmentArgs.fromBundle(it)
        }

        val dataSource = AppRepository

        // Create an instance of the ViewModel Factory.
        val viewModelFactory =
            arguments?.personNum?.let { MemberDetailViewModelFactory(it, dataSource) }

        // Get a reference to the ViewModel associated with this fragment.
        val memberDetailViewModel =
            viewModelFactory?.let {
                ViewModelProvider(
                    this, it
                ).get(MemberDetailViewModel::class.java)
            }

        // Give the binding object the preference to the view model
        binding.memberDetailViewModel = memberDetailViewModel

        // Set layoutManager for the recycler view
        binding.commentsRecyclerView.layoutManager = LinearLayoutManager(this.activity)

        val adapter = CommentAdapter()

        // Give the binding object the reference to the adapter
        binding.commentsRecyclerView.adapter = adapter

        // Set the lifeCycleOwner to this fragment
        binding.lifecycleOwner = this

        // Observe the list of member and modified the UI accordingly when chances occur
        memberDetailViewModel?.member?.observe(viewLifecycleOwner, Observer {
            binding.apply {
                Glide.with(memberImg.context)
                    .load("https://avoindata.eduskunta.fi/" + it.picture)
                    .into(memberImg)
                fullNameTv.text = setFullName(it)
                titleTv.text = if (it.minister) "Minister" else "Member of Parliament"
                ageTv.text = setAge(it)
                partyTv.text = formatParties(it)
                constituencyTv.text = it.constituency
                twitterTv.text = it.twitter
            }
        })

        val id: Int = arguments?.personNum ?: 0

        // Set onClickListener to the link that navigates to AddCommentAndRatingFragment
        binding.commentAndRateTextview.setOnClickListener {

            if (memberDetailViewModel != null) {
                view?.findNavController()?.navigate(
                    MemberDetailFragmentDirections.actionMemberDetailFragmentToAddCommentAndRatingFragment(
                        id
                    )
                )
                //Set navigateToAddCommentAndRating = null
                memberDetailViewModel.onAddCommentAndRatingNavigated()
            }
        }

        // Observe the changes in all_comments table
        memberDetailViewModel?.comments?.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        // Observe the changes in all_rating table
        memberDetailViewModel?.ratings?.observe(viewLifecycleOwner, Observer {
            val average = calculateAvg(it)
            Timber.i(average.toString())
            formatDisplayOfRatingBar(average, binding.memberRating)
        })

        return binding.root
    }

    // Set the back button to navigate back to AllMembersFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view?.findNavController()
                    ?.navigate(MemberDetailFragmentDirections.actionMemberDetailFragmentToAllMembersFragment())
            }
        })
    }
}