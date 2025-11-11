package com.example.community_union_project_ngo_mobile.ui.user

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityGoFundMeBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class GoFundMeActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityGoFundMeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoFundMeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // No specific UI setup needed
    }

    override fun setupListeners() {
        binding.btnProceed.setOnClickListener {
            val amount = binding.etAmount.text.toString()
            val intent = Intent(this, PaymentMethodActivity::class.java)
            intent.putExtra("DONATION_AMOUNT", amount)
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