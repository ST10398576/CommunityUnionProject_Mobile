package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityBeneficiaryListBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class BeneficiaryListActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityBeneficiaryListBinding

    // Placeholder for beneficiary data
    private val beneficiaries = listOf(
        Beneficiary("Conner James", "Transport"),
        Beneficiary("Vincent Abraham", "Food"),
        Beneficiary("Ethan Cole", "Housing"),
        Beneficiary("Maya Evans", "Education"),
        Beneficiary("Gabriella Hughes", "Health Care"),
        Beneficiary("Nathan Carter", "Employment")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeneficiaryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        beneficiaries.forEach {
            val beneficiaryView = createBeneficiaryView(it)
            binding.llBeneficiaryList.addView(beneficiaryView)
        }
    }

    private fun createBeneficiaryView(beneficiary: Beneficiary): LinearLayout {
        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 16, 0, 0)
            }
            setBackgroundResource(R.drawable.edit_text_background) // Re-using existing drawable
            setPadding(16, 16, 16, 16)
            setOnClickListener {
                val intent = Intent(this@BeneficiaryListActivity, UpdateBeneficiaryActivity::class.java)
                intent.putExtra("BENEFICIARY_NAME", beneficiary.name)
                intent.putExtra("ASSISTANCE_CATEGORY", beneficiary.assistanceCategory)
                startActivity(intent)
            }
        }

        val nameTextView = TextView(this).apply {
            text = beneficiary.name
            textSize = 18f
            setTextColor(resources.getColor(android.R.color.black))
        }

        val categoryTextView = TextView(this).apply {
            text = "Category of assistance: ${beneficiary.assistanceCategory}"
            setTextColor(resources.getColor(android.R.color.black))
        }

        linearLayout.addView(nameTextView)
        linearLayout.addView(categoryTextView)

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

    private data class Beneficiary(val name: String, val assistanceCategory: String)
}