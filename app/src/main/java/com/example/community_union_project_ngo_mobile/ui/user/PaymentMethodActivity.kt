package com.example.community_union_project_ngo_mobile.ui.user

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityPaymentMethodBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class PaymentMethodActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityPaymentMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val amount = intent.getStringExtra("DONATION_AMOUNT")
        binding.tvAmount.text = "R $amount"
    }

    override fun setupListeners() {
        binding.btnConfirm.setOnClickListener {
            val amount = intent.getStringExtra("DONATION_AMOUNT")
            val paymentMethod = when (binding.rgPaymentMethod.checkedRadioButtonId) {
                binding.rbInstantEft.id -> "Instant EFT Payment"
                binding.rbCreditCard.id -> "Credit Card Payment"
                binding.rbPayPal.id -> "Pay Pal Payment"
                else -> ""
            }

            val intent = Intent(this, DonationCompleteActivity::class.java)
            intent.putExtra("DONATION_AMOUNT", amount)
            intent.putExtra("PAYMENT_METHOD", paymentMethod)
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