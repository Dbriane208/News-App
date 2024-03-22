package daniel.brian.news_app.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import daniel.brian.news_app.domain.manager.LocalUserManager
import daniel.brian.news_app.presentation.util.Constants.APP_ENTRY
import daniel.brian.news_app.presentation.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
): LocalUserManager {
    override suspend fun saveAppEntry() {
        context.datastore.edit {settings ->
            settings[PreferencesKeys.APP_ENTRY_KEY] =true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.datastore.data.map {preferences ->
            preferences[PreferencesKeys.APP_ENTRY_KEY] ?: false
        }
    }

}

// having an instance from the dataStore
private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKeys{
    val APP_ENTRY_KEY = booleanPreferencesKey(name = APP_ENTRY)
}