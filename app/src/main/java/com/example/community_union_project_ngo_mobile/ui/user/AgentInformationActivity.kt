package com.example.community_union_project_ngo_mobile.ui.user

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityAgentInformationBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class AgentInformationActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityAgentInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // TODO: Implement UI setup
    }

    override fun setupListeners() {
        binding.btnContactInfo.setOnClickListener {
            val intent = Intent(this, AgentContactListActivity::class.java)
            startActivity(intent)
        }

        binding.btnFaq.setOnClickListener {
            val intent = Intent(this, FaqActivity::class.java)
            startActivity(intent)
        }

        binding.btnSupervisorEscalation.setOnClickListener {
            val intent = Intent(this, SupervisorEscalationActivity::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}