package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.community_union_project_ngo_mobile.databinding.ActivityLogTicketBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class LogTicketActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityLogTicketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val ticketCategory = intent.getStringExtra("TICKET_CATEGORY")
        binding.tvTitle.text = ticketCategory

        val beneficiaries = arrayOf("Vincent Abraham", "Conner James", "Ethan Cole")
        val beneficiaryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, beneficiaries)
        beneficiaryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnBeneficiaryName.adapter = beneficiaryAdapter

        val urgencyLevels = arrayOf(1, 2, 3, 4, 5)
        val urgencyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, urgencyLevels)
        urgencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnUrgencyLevel.adapter = urgencyAdapter
    }

    override fun setupListeners() {
        binding.btnConfirm.setOnClickListener {
            val intent = Intent(this, ListOfTicketsActivity::class.java)
            // TODO: Pass ticket data to the list activity
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