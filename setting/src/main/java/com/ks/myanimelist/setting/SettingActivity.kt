package com.ks.myanimelist.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.ks.myanimelist.di.settingModule
import com.ks.myanimelist.setting.databinding.ActivitySettingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private val settingViewModel: SettingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(settingModule)
        supportActionBar?.title = "MyAnimeList"
        observeThemeSetting()

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            settingViewModel.saveThemeSetting(isChecked)
        }
    }

    private fun observeThemeSetting() {
        settingViewModel.getThemeSetting().observe(this) { isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            binding.switchTheme.isChecked = isDarkModeActive
        }
    }
}