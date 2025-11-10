package com.example.community_union_project_ngo_mobile.ui.agent

import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.community_union_project_ngo_mobile.databinding.ActivityUpdateTicketBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class UpdateTicketActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityUpdateTicketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val ticketType = intent.getStringExtra("TICKET_TYPE")
        binding.tvTitle.text = ticketType
        
        val urgencyLevels = arrayOf(1, 2, 3, 4, 5)
        val urgencyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, urgencyLevels)
        urgencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnUrgencyLevel.adapter = urgencyAdapter

        val statuses = arrayOf("Pending", "In Progress", "Resolved")
        val statusAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statuses)
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnStatus.adapter = statusAdapter
    }

    override fun setupListeners() {
        binding.btnUpdate.setOnClickListener {
            // TODO: Implement ticket update logic
            finish()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}