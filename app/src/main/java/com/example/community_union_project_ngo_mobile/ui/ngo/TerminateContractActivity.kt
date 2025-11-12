package com.example.community_union_project_ngo_mobile.ui.ngo

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityTerminateContractBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class TerminateContractActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityTerminateContractBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTerminateContractBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // No specific UI setup needed
    }

    override fun setupListeners() {
        binding.btnSendRequest.setOnClickListener {
            // TODO: Implement logic to send termination request to admins
            val intent = Intent(this, TerminationRequestSentActivity::class.java)
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