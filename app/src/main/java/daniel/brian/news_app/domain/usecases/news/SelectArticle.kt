package daniel.brian.news_app.domain.usecases.news

import daniel.brian.news_app.data.local.NewsDao
import daniel.brian.news_app.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticle(
    private val newsDao: NewsDao
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}