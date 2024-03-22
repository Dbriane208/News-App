package daniel.brian.news_app.domain.usecases

import daniel.brian.news_app.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator  fun invoke (){
        localUserManager.saveAppEntry()
    }
}