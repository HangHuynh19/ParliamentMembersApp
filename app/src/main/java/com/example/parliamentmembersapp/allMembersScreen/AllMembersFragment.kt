//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 2 October 2021

package com.example.parliamentmembersapp.allMembersScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.parliamentmembersapp.R
import com.example.parliamentmembersapp.database.AppRepository
import com.example.parliamentmembersapp.databinding.FragmentAllMembersBinding

class AllMembersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding = DataBindingUtil.inflate<FragmentAllMembersBinding>(
            inflater,
            R.layout.fragment_all_members, container, false
        )

        val dataSource = AppRepository
        val allMembersViewModel = AllMembersViewModel(dataSource)

        // Give the binding object the preference to the view model
        binding.allMembersVM = allMembersViewModel

        // Create an instance of MemberAdapter with onClick Listener is set for each member
        val adapter = MemberAdapter(MemberListener { personNum ->
            allMembersViewModel.onMemberClicked(personNum)
        })

        // Give the binding object the reference to the adapter
        binding.memberListRv.adapter = adapter

        // Set the lifeCycleOwner to this fragment
        binding.lifecycleOwner = this

        // Observe the list of member and submit the changes
        allMembersViewModel.members.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        // Observe the event when a member is clicked. The user will be navigated to that member's page
        allMembersViewModel.navigateToMemberDetail.observe(viewLifecycleOwner, Observer { personNum ->
            personNum?.let {

                this.findNavController().navigate(
                    AllMembersFragmentDirections
                        .actionAllMembersFragmentToMemberDetailFragment(personNum))
                allMembersViewModel.onMemberDetailNavigated()

            }
        })

        // Set the grid layout manager for the recycler view with 3 columns in a row
        val manager = GridLayoutManager(activity, 3)
        binding.memberListRv.layoutManager = manager

        return binding.root
    }
}


