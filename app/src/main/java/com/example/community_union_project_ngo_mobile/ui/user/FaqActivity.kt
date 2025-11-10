package com.example.community_union_project_ngo_mobile.ui.user

import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityFaqBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class FaqActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityFaqBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // No specific UI setup needed for this static screen
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // No observers needed for this screen
    }
}