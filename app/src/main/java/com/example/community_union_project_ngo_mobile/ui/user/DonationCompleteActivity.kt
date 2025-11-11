package com.example.community_union_project_ngo_mobile.ui.user

import android.os.Bundle
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityDonationCompleteBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class DonationCompleteActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityDonationCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val amount = intent.getStringExtra("DONATION_AMOUNT")
        val paymentMethod = intent.getStringExtra("PAYMENT_METHOD")

        binding.tvAmountDonated.text = "R$amount,00"
        binding.tvPaymentMethod.text = paymentMethod

        val paymentMethodImage = when (paymentMethod) {
            "Instant EFT Payment" -> R.drawable.ic_instant_eft // Replace with your actual drawable
            "Credit Card Payment" -> R.drawable.ic_credit_card // Replace with your actual drawable
            "Pay Pal Payment" -> R.drawable.ic_paypal // Replace with your actual drawable
            else -> 0
        }
        binding.ivPaymentMethod.setImageResource(paymentMethodImage)
    }

    override fun setupListeners() {
        binding.btnHomePage.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // No observers needed
    }
}