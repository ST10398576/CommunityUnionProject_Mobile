package com.example.community_union_project_ngo_mobile.ui.agent

import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityFieldAgentUpdateContactBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class UpdateContactActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityFieldAgentUpdateContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFieldAgentUpdateContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // No specific UI setup needed
    }

    override fun setupListeners() {
        binding.btnConfirm.setOnClickListener {
            // TODO: Implement contact update logic
            finish()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // No observers needed
    }
}