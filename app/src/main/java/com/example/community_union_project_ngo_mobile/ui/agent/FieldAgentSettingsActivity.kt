package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.MainActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityFieldAgentSettingsBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class FieldAgentSettingsActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityFieldAgentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFieldAgentSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // No specific UI setup needed
    }

    override fun setupListeners() {
        binding.btnUpdateContact.setOnClickListener {
            val intent = Intent(this, UpdateContactActivity::class.java)
            startActivity(intent)
        }

        binding.btnUpdateLocation.setOnClickListener {
            val intent = Intent(this, UpdateLocationActivity::class.java)
            startActivity(intent)
        }

        binding.btnUpdatePassword.setOnClickListener {
            val intent = Intent(this, UpdatePasswordActivity::class.java)
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