package com.example.community_union_project_ngo_mobile.ui.admin

import android.os.Bundle
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityVerifyNgoBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class VerifyNgoActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityVerifyNgoBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyNgoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        userId = intent.getStringExtra("USER_ID")!!

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.tvName.text = document.getString("ngoName")
                    binding.tvRegistrationNumber.text = document.getString("registrationNumber")
                    binding.tvPboNumber.text = document.getString("pboNumber")
                    // TODO: Implement document download logic
                    binding.tvFoundationDocument.text = "foundation_document.docx"
                    binding.tvAnnualFinancialStatement.text = "annual_financial_statement.docx"
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting document: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun setupListeners() {
        binding.btnAccept.setOnClickListener {
            db.collection("users").document(userId).update("isVerified", true)
                .addOnSuccessListener {
                    // TODO: Send acceptance email
                    Toast.makeText(this, "NGO Verified", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error updating document: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnReject.setOnClickListener {
            db.collection("users").document(userId).delete()
                .addOnSuccessListener {
                    auth.getUser(userId).addOnSuccessListener { userRecord ->
                        auth.deleteUser(userRecord.uid)
                        // TODO: Send rejection email
                        Toast.makeText(this, "NGO Rejected", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error deleting document: ${e.message}", Toast.LENGTH_SHORT).show()
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