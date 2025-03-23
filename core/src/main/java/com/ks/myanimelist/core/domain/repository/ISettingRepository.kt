package com.ks.myanimelist.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface ISettingRepository {
    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(enabled: Boolean)

}