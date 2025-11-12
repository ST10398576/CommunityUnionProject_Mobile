package com.example.community_union_project_ngo_mobile.ui.admin

import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityVerifyFieldAgentBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class VerifyFieldAgentActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityVerifyFieldAgentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyFieldAgentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // TODO: Get Field Agent data from Intent and populate fields
    }

    override fun setupListeners() {
        binding.btnAccept.setOnClickListener {
            // TODO: Implement accept logic
            finish()
        }

        binding.btnReject.setOnClickListener {
            // TODO: Implement reject logic
            finish()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}