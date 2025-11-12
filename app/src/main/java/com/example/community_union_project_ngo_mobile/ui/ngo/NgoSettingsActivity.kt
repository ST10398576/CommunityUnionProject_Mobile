package com.example.community_union_project_ngo_mobile.ui.ngo

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.MainActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoSettingsBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class NgoSettingsActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityNgoSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // No specific UI setup needed
    }

    override fun setupListeners() {
        binding.btnCollaboration.setOnClickListener {
            val intent = Intent(this, NgoCollaborationActivity::class.java)
            startActivity(intent)
        }

        binding.btnTerminateContract.setOnClickListener {
            val intent = Intent(this, TerminateContractActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
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