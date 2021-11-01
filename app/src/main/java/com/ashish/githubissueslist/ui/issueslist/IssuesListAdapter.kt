package com.ashish.githubissueslist.ui.issueslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ashish.githubissueslist.R
import com.ashish.githubissueslist.databinding.IssuesItemViewBinding
import com.bumptech.glide.Glide
import db.IssuesClass

class IssuesListAdapter(
    private val onItemClick: (issues: IssuesClass) -> Unit
) : RecyclerView.Adapter<IssuesListAdapter.ViewHolder>() {
    private var items = ArrayList<IssuesClass>()

    fun addItem(items: ArrayList<IssuesClass>) {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.issues_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = IssuesItemViewBinding.bind(itemView)

        fun bind(issue: IssuesClass) = with(itemView) {
            issue.apply {
                binding.apply {
                    issueTitleText.text = issueTitle
                    issueDescription.let {
                        issueDescriptionText.isVisible = true
                        issueDescriptionText.text = it
                    }
                    issueCreatedUserDetailText.text = issueCreatedBy
                    issueCreatedDateText.text = issueCreatedDate
                    setOnClickListener {
                        onItemClick(issue)
                    }
                }
            }
            Glide.with(this)
                .load(issue.avatarUrl)
                .error(Glide.with(binding.avatarImage).load(R.drawable.ic_baseline_group_24))
                .into(binding.avatarImage)
        }
    }
}