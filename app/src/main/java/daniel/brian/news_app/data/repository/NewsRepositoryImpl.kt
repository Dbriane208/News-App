package daniel.brian.news_app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import daniel.brian.news_app.data.local.NewsDao
import daniel.brian.news_app.data.remote.NewsApi
import daniel.brian.news_app.data.remote.NewsPagingSource
import daniel.brian.news_app.data.remote.SearchNewsPagingSource
import daniel.brian.news_app.domain.model.Article
import daniel.brian.news_app.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
): NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override suspend fun selectArticleById(url: String): Article? {
        return newsDao.getArticleById(url)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}