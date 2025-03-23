package com.ks.myanimelist.core.domain.usecase

import com.ks.myanimelist.core.domain.repository.ISettingRepository

class SettingInteractor(private val settingRepository: ISettingRepository): SettingUseCase {
    override fun getThemeSetting() = settingRepository.getThemeSetting()
    override suspend fun saveThemeSetting(enabled: Boolean) = settingRepository.saveThemeSetting(enabled)
}