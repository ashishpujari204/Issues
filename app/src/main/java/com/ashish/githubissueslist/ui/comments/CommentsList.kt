package com.ashish.githubissueslist.ui.comments

import android.os.Bundle
import androidx.core.view.isVisible
import com.ashish.githubissueslist.R
import com.ashish.githubissueslist.base.BaseActivity
import com.ashish.githubissueslist.databinding.ActivityCommentsListBinding
import com.ashish.githubissueslist.ui.issueslist.IssuesList
import com.ashish.githubissueslist.ui.issueslist.IssuesPresentation
import db.CommentsClass
import db.CommentsDBHelperImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import util.Constants
import util.Util

class CommentsList : BaseActivity<ActivityCommentsListBinding, CommentsListViewModel>() {

    override val viewModel: CommentsListViewModel by inject()
    private lateinit var dbHelper: CommentsDBHelperImpl
    private val commentAdapter: CommentsListAdapter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments_list)
        initial()
    }

    private fun initial() {
        dbHelper = Util.getCommentsDBHelper(this)
        if (Constants.isInternetAvailable(this)) {
            intent.getStringExtra(IssuesList.COMMENT_NUMBER)?.let { viewModel.getComments(it) }
        } else {
            CoroutineScope(Dispatchers.Default).launch {
                if (dbHelper.getRowCount() > 0) {
                    commentAdapter.apply {
                        addItem(dbHelper.getComments() as ArrayList<CommentsClass>)
                        binding.comemntsListView.adapter = this
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

    override fun getViewBinding(): ActivityCommentsListBinding =
        ActivityCommentsListBinding.inflate(layoutInflater)

    override fun onObserve() {
        viewModel.uiState().observe(this, { commentList ->
            showComment(commentList)
        })
    }

    private fun showComment(commentList: CommentsState?) {
        commentList?.let {
            when (it) {
                is CommentsState.Error -> showToast(it.message)
                is CommentsState.Loading -> showProgressBar(it.isLoading)
                is CommentsState.Success -> {
                    val issueArray =
                        IssuesPresenter(it.entries as ArrayList<CommentsClass>).getCommentList() as ArrayList<IssuesPresentation>
                    CoroutineScope(Dispatchers.Main).launch {
                        issueArray.forEach { comment ->
                            dbHelper.insertOrUpdate(
                                CommentsClass(
                                    comment.issueId,
                                    comment.issueId,
                                    comment.issueCreatedDate,
                                    comment.avatarUrl,
                                    comment.issueTitle,
                                    comment.issueCreatedBy,
                                    comment.commentNumber
                                )
                            )
                        }
                        commentAdapter.apply {
                            addItem(dbHelper.getComments() as ArrayList<CommentsClass>)
                            binding.comemntsListView.adapter = this
                        }
                    }
                }
            }
        }
    }

    private fun showProgressBar(showProgressBar: Boolean) {
        binding.progressBar.isVisible = showProgressBar
    }
}