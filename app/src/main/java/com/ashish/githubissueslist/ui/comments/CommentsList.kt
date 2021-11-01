package com.ashish.githubissueslist.ui.comments

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ashish.githubissueslist.R
import com.ashish.githubissueslist.base.BaseActivity
import com.ashish.githubissueslist.databinding.ActivityCommentsListBinding
import com.ashish.githubissueslist.model.CommentsModelClass
import com.ashish.githubissueslist.ui.issueslist.IssuesList
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
    private lateinit var commentListView: RecyclerView
    var clickedCommentId: String = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments_list)
        commentListView = findViewById(R.id.commentsListView)
        initial()
    }

    private fun initial() {
        dbHelper = Util.getCommentsDBHelper(this)
        clickedCommentId = intent.getStringExtra(IssuesList.COMMENT_NUMBER).toString()
        if (Constants.isInternetAvailable(this)) {
            viewModel.getComments(clickedCommentId)
        } else {
            CoroutineScope(Dispatchers.Default).launch {
                if (dbHelper.getRowCount(clickedCommentId) > 0) {
                    commentAdapter.apply {
                        addItem(dbHelper.getComments(clickedCommentId) as ArrayList<CommentsClass>)
                        commentListView.adapter = this
                    }
                } else {
                    runOnUiThread {
                        run {
                            showToast(getString(R.string.no_comments))
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
                    if (it.entries.isEmpty()) {
                        showToast("No comments found")
                        return
                    }
                    val commentArray =
                        CommentsPresenter(it.entries as ArrayList<CommentsModelClass>).getCommentList() as ArrayList<CommentPresentation>
                    CoroutineScope(Dispatchers.Main).launch {
                        commentArray.forEach { comment ->
                            dbHelper.insertOrUpdate(
                                CommentsClass(
                                    comment.commentId,
                                    commentId = clickedCommentId.toInt(),
                                    comment.commentCreatedDate,
                                    comment.avatarUrl,
                                    comment.commentTitle,
                                    comment.commentCreatedBy,
                                    comment.commentNumber
                                )
                            )
                        }
                        commentAdapter.apply {
                            commentAdapter.addItem(dbHelper.getComments(clickedCommentId) as ArrayList<CommentsClass>)
                            commentListView.adapter = this
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