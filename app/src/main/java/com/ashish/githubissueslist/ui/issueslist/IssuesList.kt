package com.ashish.githubissueslist.ui.issueslist

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.ashish.githubissueslist.R
import com.ashish.githubissueslist.base.BaseActivity
import com.ashish.githubissueslist.databinding.ActivityIssuesListBinding
import com.ashish.githubissueslist.model.IssuesModel
import com.ashish.githubissueslist.ui.comments.CommentsList
import com.ashish.githubissueslist.ui.issueslist.IssuesListState.*
import db.IssueDBHelperImpl
import db.IssuesClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import util.Constants
import util.Util

class IssuesList : BaseActivity<ActivityIssuesListBinding, IssuesListViewModel>() {

    override val viewModel: IssuesListViewModel by inject()
    private lateinit var issueAdapter: IssuesListAdapter
    private lateinit var dbHelper: IssueDBHelperImpl

    companion object {
        const val COMMENT_NUMBER = "comment_number"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        dbHelper = Util.getDBHelper(this)
        if (Constants.isInternetAvailable(this)) {
            viewModel.getIssues()
        } else {
            CoroutineScope(Dispatchers.Default).launch {
                if (dbHelper.getRowCount() > 0) {
                    issueAdapter =
                        IssuesListAdapter { issueItem: IssuesClass -> onItemClickEvent(issueItem) }
                    issueAdapter.apply {
                        addItem(dbHelper.getIssues() as ArrayList<IssuesClass>)
                        binding.issuesListView.adapter = this
                    }
                } else {
                    runOnUiThread {
                        run {
                            showErrorDialog(getString(R.string.internet_error))
                        }
                    }
                }
            }
        }
    }

    override fun onObserve() {
        viewModel.uiState().observe(this, { issuesList ->
            showIssuesList(issuesList)
        })
    }

    private fun showIssuesList(issuesList: IssuesListState?) {
        issuesList?.let {
            when (it) {
                is Error -> showToast(it.message)
                is Loading -> showProgressBar(it.isLoading)
                is Success -> {
                    val issueArray =
                        IssuesPresenter(it.entries as ArrayList<IssuesModel>).getIssueList() as ArrayList<IssuesPresentation>
                    CoroutineScope(Dispatchers.Main).launch {
                        issueArray.forEach { issues ->
                            issues.issueDescription?.let { description ->
                                IssuesClass(
                                    issues.issueId,
                                    issues.issueId,
                                    issues.issueCreatedDate,
                                    issues.avatarUrl,
                                    issues.issueTitle,
                                    description,
                                    issues.issueCreatedBy,
                                    issues.commentNumber
                                )
                            }?.let { issueClass ->
                                dbHelper.insertOrUpdate(
                                    issueClass
                                )
                            }
                        }
                        issueAdapter =
                            IssuesListAdapter { issueItem: IssuesClass -> onItemClickEvent(issueItem) }
                        issueAdapter.apply {
                            addItem(dbHelper.getIssues() as ArrayList<IssuesClass>)
                            binding.issuesListView.adapter = this
                        }
                    }
                }
            }
        }
    }

    private fun onItemClickEvent(issueItem: IssuesClass) {
        startActivity(Intent(this@IssuesList, CommentsList::class.java).apply {
            putExtra(COMMENT_NUMBER, issueItem.commentNumber)
        })
    }

    private fun showProgressBar(showProgressBar: Boolean) {
        binding.progressBar.isVisible = showProgressBar
    }

    override fun getViewBinding(): ActivityIssuesListBinding =
        ActivityIssuesListBinding.inflate(layoutInflater)
}