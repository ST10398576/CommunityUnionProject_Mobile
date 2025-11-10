package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityFieldAgentLoginBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class FieldAgentLoginActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityFieldAgentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFieldAgentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // TODO: Implement UI setup
    }

    override fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            // TODO: Authenticate field agent
            val agentId = binding.etAgentId.text.toString()
            val intent = Intent(this, FieldAgentHomeActivity::class.java)
            intent.putExtra("AGENT_NAME", agentId) 
            startActivity(intent)
        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, FieldAgentRegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}