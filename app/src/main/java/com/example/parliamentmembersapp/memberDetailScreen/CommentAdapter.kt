//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 6 October 2021

package com.example.parliamentmembersapp.memberDetailScreen

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentmembersapp.database.Comment
import com.example.parliamentmembersapp.databinding.CommentItemLayoutBinding

class CommentAdapter : ListAdapter<Comment, CommentAdapter.ViewHolder>(
    CommentDiffCallBack()
) {
    class ViewHolder private constructor(private val binding: CommentItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Bind the view with the data it needs to display
        fun bind(item: Comment) {
            binding.comment = item
            binding.executePendingBindings()
        }

        // Inflate the view of individual item
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CommentItemLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("aaa", "recyclerview inflated")
        return ViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        Log.i("aaa", "recyclerview inflated")
    }
}

// Compare the old and new item to see if it has been modified
class CommentDiffCallBack : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(
        oldItem: Comment,
        newItem: Comment
    ): Boolean {
        return oldItem.commentId == newItem.commentId
    }

    override fun areContentsTheSame(
        oldItem: Comment,
        newItem: Comment
    ): Boolean {
        return oldItem == newItem
    }
}
