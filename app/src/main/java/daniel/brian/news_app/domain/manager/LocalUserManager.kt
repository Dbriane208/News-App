package daniel.brian.news_app.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    // we want to save the user app entry when s/he clicks get started
    suspend fun saveAppEntry()
    fun readAppEntry(): Flow<Boolean>
}