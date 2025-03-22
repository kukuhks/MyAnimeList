package com.ks.myanimelist.di

import com.ks.myanimelist.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingModule = module {
    viewModel { SettingViewModel(get()) }
}

