package com.example.community_union_project_ngo_mobile.ui.user

import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityAgentDetailBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class AgentDetailActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityAgentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val agentName = intent.getStringExtra("AGENT_NAME")
        binding.btnCallAgent.text = "Call $agentName"

        val agentDetails = when (agentName) {
            "David Sunday" -> "Agent is currently busy.\nAgent Operational Area: Somerset West\nAgent Number: +27 61 160 6769"
            "Megan Cross" -> "Agent is available.\nAgent Operational Area: Stellenbosch\nAgent Number: +27 72 345 6789"
            else -> "No details available."
        }
        binding.tvAgentDetails.text = agentDetails
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}