package daniel.brian.news_app.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import daniel.brian.news_app.data.manager.LocalUserManagerImpl
import daniel.brian.news_app.domain.manager.LocalUserManager
import daniel.brian.news_app.domain.usecases.AppEntryUseCases
import daniel.brian.news_app.domain.usecases.ReadAppEntry
import daniel.brian.news_app.domain.usecases.SaveAppEntry
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
}