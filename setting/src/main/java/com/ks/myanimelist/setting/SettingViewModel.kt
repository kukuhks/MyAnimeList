package com.ks.myanimelist.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ks.myanimelist.core.domain.usecase.SettingUseCase
import kotlinx.coroutines.launch

class SettingViewModel(private val settingUseCase: SettingUseCase) : ViewModel() {
    val getThemeSetting = settingUseCase.getThemeSetting().asLiveData()
    fun saveThemeSetting(state: Boolean) {
        viewModelScope.launch {
            settingUseCase.saveThemeSetting(state)
        }
    }
}