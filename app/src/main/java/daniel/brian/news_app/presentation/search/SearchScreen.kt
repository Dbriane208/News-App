package daniel.brian.news_app.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import daniel.brian.news_app.domain.model.Article
import daniel.brian.news_app.presentation.commons.ArticleList
import daniel.brian.news_app.presentation.commons.SearchBar
import daniel.brian.news_app.presentation.nvgraph.Route
import daniel.brian.news_app.presentation.util.Dimens.MediumPadding1

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {event(SearchEvent.SearchNews)}
        )

        Spacer(modifier = Modifier.height(MediumPadding1))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles = articles, onClick = { article ->
               navigateToDetails(article)
            })
        }
    }
}