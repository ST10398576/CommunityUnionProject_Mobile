package com.example.community_union_project_ngo_mobile.ui.admin

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityPendingVerificationBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

data class VerificationItem(val type: String, val name: String, val details: String)

class PendingVerificationActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityPendingVerificationBinding

    // Placeholder for verification data
    private val items = listOf(
        VerificationItem("NGO/NPO", "Gift of the Givers", "Registration Number: 0000000000\nPBO Number: 0000000000\nStatus: Pending"),
        VerificationItem("Field Agent", "David Sunday", "Contact Number: 012 345 6789\nAssociated NGO: Gift of the Givers\nLocation: Cape Town\nStatus: Pending"),
        VerificationItem("Field Agent", "Vivian Scott", "Contact Number: 012 345 6789\nAssociated NGO: Lunch Box Foundation\nLocation: Cape Town\nStatus: Pending")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendingVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        items.forEach { item ->
            val itemView = createVerificationItemView(item)
            binding.llVerificationList.addView(itemView)
        }
    }

    private fun createVerificationItemView(item: VerificationItem): LinearLayout {
        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 16, 0, 0)
            }
            setBackgroundResource(R.drawable.edit_text_background)
            setPadding(16, 16, 16, 16)
            setOnClickListener {
                val intent = if (item.type == "NGO/NPO") {
                    Intent(this@PendingVerificationActivity, VerifyNgoActivity::class.java)
                } else {
                    Intent(this@PendingVerificationActivity, VerifyFieldAgentActivity::class.java)
                }
                startActivity(intent)
            }
        }

        val typeTextView = TextView(this).apply {
            text = item.type
            textSize = 18f
            setTextColor(resources.getColor(android.R.color.black))
        }

        val detailsTextView = TextView(this).apply {
            text = "Name: ${item.name}\n${item.details}"
            setTextColor(resources.getColor(android.R.color.black))
        }

        linearLayout.addView(typeTextView)
        linearLayout.addView(detailsTextView)

        return linearLayout
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}