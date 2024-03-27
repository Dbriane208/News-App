package daniel.brian.news_app.presentation.bookmark

import daniel.brian.news_app.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)