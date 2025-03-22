package com.ks.myanimelist.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ks.myanimelist.core.datastore.SettingPreferences
import kotlinx.coroutines.launch

class SettingViewModel(private val settingPreferences: SettingPreferences) : ViewModel() {
    fun getThemeSetting(): LiveData<Boolean> {
        return settingPreferences.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(enabled: Boolean) {
        viewModelScope.launch {
            settingPreferences.saveThemeSetting(enabled)
        }

    }
}