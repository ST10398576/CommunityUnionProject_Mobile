package com.example.community_union_project_ngo_mobile.ui.user

import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityUpdateContactBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class UpdateContactActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityUpdateContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateContactBinding.inflate(layoutInflater)
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