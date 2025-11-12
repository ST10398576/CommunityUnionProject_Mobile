package com.example.community_union_project_ngo_mobile.ui.agent

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityUpdateBeneficiaryBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.firestore.FirebaseFirestore

class UpdateBeneficiaryActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityUpdateBeneficiaryBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var beneficiaryId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBeneficiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        beneficiaryId = intent.getStringExtra("BENEFICIARY_ID")!!

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val incomeLevels = arrayOf("R 1000 - R 2500", "R 2501 - R 5000", "R 5001 - R 10000")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, incomeLevels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnIncomeLevel.adapter = adapter

        db.collection("beneficiaries").document(beneficiaryId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.etFullName.setText(document.getString("fullName"))
                    binding.etCategoryOfAssistance.setText(document.getString("categoryOfAssistance"))
                    binding.etAssociatedNgoId.setText(document.getString("associatedNgoId"))
                    binding.etLocation.setText(document.getString("location"))
                    binding.spnIncomeLevel.setSelection(adapter.getPosition(document.getString("incomeLevel")))
                    binding.etContactNumber.setText(document.getString("contactNumber"))
                    binding.etReason.setText(document.getString("reason"))
                }
            }
    }

    override fun setupListeners() {
        binding.btnUpdate.setOnClickListener {
            val beneficiary = hashMapOf(
                "fullName" to binding.etFullName.text.toString(),
                "categoryOfAssistance" to binding.etCategoryOfAssistance.text.toString(),
                "associatedNgoId" to binding.etAssociatedNgoId.text.toString(),
                "location" to binding.etLocation.text.toString(),
                "incomeLevel" to binding.spnIncomeLevel.selectedItem.toString(),
                "contactNumber" to binding.etContactNumber.text.toString(),
                "reason" to binding.etReason.text.toString(),
            )

            db.collection("beneficiaries").document(beneficiaryId).update(beneficiary as Map<String, Any>)
                .addOnSuccessListener {
                    Toast.makeText(this, "Beneficiary updated successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error updating beneficiary: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnDelete.setOnClickListener {
            db.collection("beneficiaries").document(beneficiaryId).delete()
                .addOnSuccessListener {
                    Toast.makeText(this, "Beneficiary deleted successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error deleting beneficiary: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // No observers needed
    }
}