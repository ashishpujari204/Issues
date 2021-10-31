package di

import com.ashish.githubissueslist.ui.issueslist.IssuesListAdapter
import com.ashish.githubissueslist.ui.issueslist.IssuesListViewModel
import org.koin.dsl.module
import rest.RepositoryImplementation

val viewModelModule = module {
    factory { IssuesListViewModel(get()) }
}

val repoImplementation = module {
    factory { RepositoryImplementation(get()) }
}

val adapterModule = module {
    single { IssuesListAdapter() }
}