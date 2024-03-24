package daniel.brian.news_app.data.remote.dtos

import daniel.brian.news_app.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)