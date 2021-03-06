package di

import com.ashish.githubissueslist.ui.comments.CommentsListAdapter
import com.ashish.githubissueslist.ui.comments.CommentsListViewModel
import com.ashish.githubissueslist.ui.issueslist.IssuesListViewModel
import org.koin.dsl.module
import rest.RepositoryImplementation

val viewModelModule = module {
    factory { IssuesListViewModel(get()) }
    factory { CommentsListViewModel(get()) }
}

val repoImplementation = module {
    factory { RepositoryImplementation(get()) }
}

val adapterModule = module {
    single { CommentsListAdapter() }
}