package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.MainActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityFieldAgentUpdatePasswordBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class UpdatePasswordActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityFieldAgentUpdatePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFieldAgentUpdatePasswordBinding.inflate(layoutInflater)
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
            // TODO: Implement password update logic
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // No observers needed
    }
}