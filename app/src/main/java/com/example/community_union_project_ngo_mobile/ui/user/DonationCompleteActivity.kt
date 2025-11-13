package com.example.community_union_project_ngo_mobile.ui.user

import android.content.Intent
import android.net.Uri
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
        binding.btnEmailReceipt.setOnClickListener {
            val amount = intent.getStringExtra("DONATION_AMOUNT")
            val paymentMethod = intent.getStringExtra("PAYMENT_METHOD")
            // TODO: Get the user's actual email address
            val userEmail = "test@example.com"

            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // Only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, arrayOf(userEmail))
                putExtra(Intent.EXTRA_SUBJECT, "Donation Receipt")
                putExtra(Intent.EXTRA_TEXT, "Thank you for your donation of R$amount,00 via $paymentMethod.")
            }

            if (emailIntent.resolveActivity(packageManager) != null) {
                startActivity(emailIntent)
            }
        }

        binding.btnHomePage.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // No observers needed
    }
}