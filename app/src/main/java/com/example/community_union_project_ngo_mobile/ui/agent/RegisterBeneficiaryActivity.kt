package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityRegisterBeneficiaryBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterBeneficiaryActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityRegisterBeneficiaryBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBeneficiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

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
            val beneficiary = hashMapOf(
                "fullName" to binding.etFullName.text.toString(),
                "categoryOfAssistance" to binding.etCategoryOfAssistance.text.toString(),
                "associatedNgoId" to binding.etAssociatedNgoId.text.toString(),
                "location" to binding.etLocation.text.toString(),
                "incomeLevel" to binding.spnIncomeLevel.selectedItem.toString(),
                "contactNumber" to binding.etContactNumber.text.toString(),
                "reason" to binding.etReason.text.toString(),
                "registeredBy" to auth.currentUser?.uid
            )

            db.collection("beneficiaries").add(beneficiary)
                .addOnSuccessListener {
                    Toast.makeText(this, "Beneficiary registered successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, BeneficiaryListActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error registering beneficiary: ${e.message}", Toast.LENGTH_SHORT).show()
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