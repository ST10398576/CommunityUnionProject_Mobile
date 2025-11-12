package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityBeneficiaryListBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.firestore.FirebaseFirestore

data class Beneficiary(val id: String, val name: String, val assistanceCategory: String)

class BeneficiaryListActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityBeneficiaryListBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeneficiaryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        db.collection("beneficiaries").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val beneficiary = Beneficiary(
                        document.id,
                        document.getString("fullName") ?: "",
                        document.getString("categoryOfAssistance") ?: ""
                    )
                    val beneficiaryView = createBeneficiaryView(beneficiary)
                    binding.llBeneficiaryList.addView(beneficiaryView)
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting documents: ${exception.message}", Toast.LENGTH_SHORT).show()
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
                intent.putExtra("BENEFICIARY_ID", beneficiary.id)
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
}