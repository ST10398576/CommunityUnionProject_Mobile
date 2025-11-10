package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.community_union_project_ngo_mobile.databinding.ActivityRegisterBeneficiaryBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class RegisterBeneficiaryActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityRegisterBeneficiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBeneficiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val incomeLevels = arrayOf("R 1000 - R 2500", "R 2501 - R 5000", "R 5001 - R 10000")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, incomeLevels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnIncomeLevel.adapter = adapter
    }

    override fun setupListeners() {
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, BeneficiaryListActivity::class.java)
            // TODO: Pass beneficiary data to the list activity
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