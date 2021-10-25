//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 2 October 2021

package com.example.parliamentmembersapp.allMembersScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.databinding.MemberItemLayoutBinding

class MemberAdapter(private val clickListener: MemberListener) : ListAdapter<MemberOfParliament, MemberAdapter.ViewHolder>(
    MemberOfParliamentDiffCallBack()
) {
    class ViewHolder private constructor(private val binding: MemberItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Bind the view with the data it needs to display
        fun bind(item: MemberOfParliament, clickListener: MemberListener) {
            binding.member = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        // Inflate the view of individual item
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MemberItemLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

// Compare the old and new item to see if it has been modified
class MemberOfParliamentDiffCallBack : DiffUtil.ItemCallback<MemberOfParliament>() {
    override fun areItemsTheSame(
        oldItem: MemberOfParliament,
        newItem: MemberOfParliament
    ): Boolean {
        return oldItem.personNumber == newItem.personNumber
    }

    override fun areContentsTheSame(
        oldItem: MemberOfParliament,
        newItem: MemberOfParliament
    ): Boolean {
        return oldItem == newItem
    }
}

// Apply the onClick listener to individual member's view
class MemberListener(val clickListener: (personNum: Int) -> Unit) {
    fun onClick(member: MemberOfParliament) = clickListener(member.personNumber)
}
