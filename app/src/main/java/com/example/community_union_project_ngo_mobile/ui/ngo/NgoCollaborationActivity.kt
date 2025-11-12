package com.example.community_union_project_ngo_mobile.ui.ngo

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoCollaborationBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.example.community_union_project_ngo_mobile.ui.user.PartnerNgoListActivity

class NgoCollaborationActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityNgoCollaborationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoCollaborationBinding.inflate(layoutInflater)
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
            val intent = Intent(this, PartnerNgoListActivity::class.java)
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