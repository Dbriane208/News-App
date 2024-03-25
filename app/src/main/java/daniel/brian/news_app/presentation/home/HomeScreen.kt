package daniel.brian.news_app.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import daniel.brian.news_app.R
import daniel.brian.news_app.domain.model.Article
import daniel.brian.news_app.presentation.commons.ArticleList
import daniel.brian.news_app.presentation.commons.SearchBar
import daniel.brian.news_app.presentation.nvgraph.Route
import daniel.brian.news_app.presentation.util.Dimens.mediumPadding1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate: (String) -> Unit
) {
    val titles by remember {
        // using derivedStateOf because we're depending on another composable
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = "\uD83d\uDFE5") {
                        it.title
                    }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = mediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = mediumPadding1)
        )

        Spacer(modifier = Modifier.height(mediumPadding1))
        
        SearchBar(
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigate(Route.SearchScreen.route)
            },
            onSearch = {}
        ) 
        
        Spacer(modifier = Modifier.height(mediumPadding1))

        Text(text = titles, modifier = Modifier
            .fillMaxWidth()
            .padding(start = mediumPadding1)
            .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
            )

        Spacer(modifier = Modifier.height(mediumPadding1))
        ArticleList(modifier = Modifier.padding(horizontal = mediumPadding1), articles = articles, onClick = {
            navigate(Route.DetailsScreen.route)
        })
    }
}