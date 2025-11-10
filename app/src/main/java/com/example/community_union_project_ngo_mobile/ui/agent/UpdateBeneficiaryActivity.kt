package com.example.community_union_project_ngo_mobile.ui.agent

import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.community_union_project_ngo_mobile.databinding.ActivityUpdateBeneficiaryBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class UpdateBeneficiaryActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityUpdateBeneficiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBeneficiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // TODO: Get beneficiary data from Intent and populate fields
        val incomeLevels = arrayOf("R 1000 - R 2500", "R 2501 - R 5000", "R 5001 - R 10000")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, incomeLevels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnIncomeLevel.adapter = adapter
    }

    override fun setupListeners() {
        binding.btnUpdate.setOnClickListener {
            // TODO: Implement update logic
            finish()
        }

        binding.btnDelete.setOnClickListener {
            // TODO: Implement delete logic
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