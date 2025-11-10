package com.example.community_union_project_ngo_mobile.ui.user

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.MainActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityUserSettingsBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class UserSettingsActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityUserSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserSettingsBinding.inflate(layoutInflater)
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