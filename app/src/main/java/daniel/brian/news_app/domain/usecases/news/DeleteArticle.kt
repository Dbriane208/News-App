package daniel.brian.news_app.domain.usecases.news

import daniel.brian.news_app.data.local.NewsDao
import daniel.brian.news_app.domain.model.Article
import daniel.brian.news_app.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}