package com.ks.myanimelist.core.data.source

import com.ks.myanimelist.core.datastore.SettingPreferences
import com.ks.myanimelist.core.domain.repository.ISettingRepository
import kotlinx.coroutines.flow.Flow

class SettingRepository(private val settingPreferences: SettingPreferences): ISettingRepository {
    override fun getThemeSetting(): Flow<Boolean> {
        return settingPreferences.getThemeSetting()
    }
    override suspend fun saveThemeSetting(enabled: Boolean) {
        settingPreferences.saveThemeSetting(enabled)
    }
}