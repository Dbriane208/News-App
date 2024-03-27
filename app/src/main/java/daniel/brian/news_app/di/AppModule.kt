package daniel.brian.news_app.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import daniel.brian.news_app.data.local.NewsDao
import daniel.brian.news_app.data.local.NewsDatabase
import daniel.brian.news_app.data.local.NewsTypeConvertor
import daniel.brian.news_app.data.manager.LocalUserManagerImpl
import daniel.brian.news_app.data.remote.NewsApi
import daniel.brian.news_app.data.repository.NewsRepositoryImpl
import daniel.brian.news_app.domain.manager.LocalUserManager
import daniel.brian.news_app.domain.repository.NewsRepository
import daniel.brian.news_app.domain.usecases.app_entry.AppEntryUseCases
import daniel.brian.news_app.domain.usecases.app_entry.ReadAppEntry
import daniel.brian.news_app.domain.usecases.app_entry.SaveAppEntry
import daniel.brian.news_app.domain.usecases.news.DeleteArticle
import daniel.brian.news_app.domain.usecases.news.GetNews
import daniel.brian.news_app.domain.usecases.news.NewsUseCases
import daniel.brian.news_app.domain.usecases.news.SearchNews
import daniel.brian.news_app.domain.usecases.news.SelectArticle
import daniel.brian.news_app.domain.usecases.news.UpsertArticle
import daniel.brian.news_app.presentation.util.Constants.BASE_URL
import daniel.brian.news_app.presentation.util.Constants.NEWS_DATABASE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ):NewsRepository = NewsRepositoryImpl(newsApi = newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticle = SelectArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}
