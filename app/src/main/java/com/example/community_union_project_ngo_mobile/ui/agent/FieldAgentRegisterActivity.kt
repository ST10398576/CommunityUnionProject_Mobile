package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityFieldAgentRegisterBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class FieldAgentRegisterActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityFieldAgentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFieldAgentRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // TODO: Implement UI setup
    }

    override fun setupListeners() {
        binding.btnRegister.setOnClickListener {
            // TODO: Register field agent
            val intent = Intent(this, FieldAgentLoginActivity::class.java)
            startActivity(intent)
        }

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, FieldAgentLoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}