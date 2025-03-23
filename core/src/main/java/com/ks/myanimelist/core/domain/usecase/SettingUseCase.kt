package com.ks.myanimelist.core.domain.usecase

import kotlinx.coroutines.flow.Flow

interface SettingUseCase {
    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(enabled: Boolean)
}