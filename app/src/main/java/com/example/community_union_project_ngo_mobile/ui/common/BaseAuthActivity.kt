package com.example.community_union_project_ngo_mobile.ui.common

import androidx.appcompat.app.AppCompatActivity

abstract class BaseAuthActivity : AppCompatActivity() {

    protected abstract fun setupUI()
    protected abstract fun setupListeners()
    protected abstract fun setupObservers()
}