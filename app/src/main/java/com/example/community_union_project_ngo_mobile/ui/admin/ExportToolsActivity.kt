package com.example.community_union_project_ngo_mobile.ui.admin

import android.os.Bundle
import android.view.View
import com.example.community_union_project_ngo_mobile.databinding.ActivityExportToolsBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class ExportToolsActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityExportToolsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExportToolsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // No specific UI setup needed
    }

    override fun setupListeners() {
        binding.btnExportAidRequests.setOnClickListener {
            // TODO: Implement PDF export logic for aid requests
            binding.btnPdfExportSuccess.visibility = View.VISIBLE
        }

        binding.btnExportAidOfferings.setOnClickListener {
            // TODO: Implement PDF export logic for aid offerings
            binding.btnPdfExportSuccess.visibility = View.VISIBLE
        }

        binding.btnExportSyncLogs.setOnClickListener {
            // TODO: Implement PDF export logic for sync logs
            binding.btnPdfExportSuccess.visibility = View.VISIBLE
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // No observers needed
    }
}