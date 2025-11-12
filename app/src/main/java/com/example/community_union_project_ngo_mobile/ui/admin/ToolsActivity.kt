package com.example.community_union_project_ngo_mobile.ui.admin

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityToolsBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class ToolsActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityToolsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToolsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // No specific UI setup needed
    }

    override fun setupListeners() {
        binding.btnExportTools.setOnClickListener {
            val intent = Intent(this, ExportToolsActivity::class.java)
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