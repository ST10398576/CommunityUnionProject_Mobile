package com.example.community_union_project_ngo_mobile.ui.ngo

import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityTerminationRequestSentBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class TerminationRequestSentActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityTerminationRequestSentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTerminationRequestSentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // No specific UI setup needed
    }

    override fun setupListeners() {
        binding.btnBackToNgoSettings.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // No observers needed
    }
}