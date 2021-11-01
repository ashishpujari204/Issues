package com.ashish.githubissueslist.ui.comments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashish.githubissueslist.R
import com.ashish.githubissueslist.databinding.CommentItemViewBinding
import com.bumptech.glide.Glide
import db.CommentsClass

class CommentsListAdapter : RecyclerView.Adapter<CommentsListAdapter.ViewHolder>() {
    private var items = ArrayList<CommentsClass>()

    fun addItem(items: ArrayList<CommentsClass>) {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.comment_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val binding = CommentItemViewBinding.bind(itemView)
            fun bind(comment: CommentsClass) = with(itemView) {
                try
                {
                comment.apply {
                    binding.apply {
                        commentTitleText.text = commentTitle
                        commentCreatedUserDetailText.text = commentCreatedBy
                        commentCreatedDateText.text = commentCreatedDate
                    }
                }
                Glide.with(this)
                    .load(comment.avatarUrl)
                    .error(
                        Glide.with(binding.commentAvatarImage).load(R.drawable.ic_baseline_group_24)
                    )
                    .into(binding.commentAvatarImage)
                }catch (exception: Exception) {
                    Log.e("exception", "exceptipon...${exception}")
                }
            }
    }
}