package daniel.brian.news_app.domain.usecases.news

import daniel.brian.news_app.data.local.NewsDao
import daniel.brian.news_app.domain.model.Article
import daniel.brian.news_app.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
   private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}