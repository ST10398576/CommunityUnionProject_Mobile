package com.example.community_union_project_ngo_mobile.ui.user

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.community_union_project_ngo_mobile.databinding.ActivitySupervisorEscalationBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class SupervisorEscalationActivity : BaseAuthActivity() {
    private lateinit var binding: ActivitySupervisorEscalationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupervisorEscalationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val categories = arrayOf("Technical Issue", "Billing Issue", "General Inquiry")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnCategory.adapter = adapter
    }

    override fun setupListeners() {
        binding.btnSubmitEscalation.setOnClickListener {
            val intent = Intent(this, EscalationSentActivity::class.java)
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