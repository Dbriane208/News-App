package daniel.brian.news_app.presentation.commons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import daniel.brian.news_app.domain.model.Article
import daniel.brian.news_app.presentation.util.Dimens
import daniel.brian.news_app.presentation.util.Dimens.ExtraSmallPadding2
import daniel.brian.news_app.presentation.util.Dimens.mediumPadding1

@Composable
// responsible for showing the list of articles we get from paging 3
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit // send the clicked article
) {
    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(mediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(count = articles.itemCount){
                articles[it]?.let {
                    ArticleCard(article = it, onClick = {onClick(it)})
                }
            }
        }
    }
}

@Composable
// handling the state of our paging
// if the return is true we know we have retrieved a list of articles successfully
fun handlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean {
    val loadState = articles.loadState
    val error = when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when{
        loadState.refresh is LoadState.Loading -> {
            // show the shimmer effect
            ShimmerEffect()
            false // because we did not retrieve any articles yet
            }
        // if error is not loading and we have null then we know there is an error
        error != null ->{
            // here we will show an empty screen
            EmptyScreen()
            false
        }
        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(mediumPadding1)) {
        // taking 10 articles
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = mediumPadding1)
            )
        }
    }

}