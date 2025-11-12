package com.example.community_union_project_ngo_mobile.ui.agent

import android.os.Bundle
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityFieldAgentUpdateContactBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UpdateContactActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityFieldAgentUpdateContactBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFieldAgentUpdateContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userId = auth.currentUser!!.uid

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.etOldPhoneNumber.setText(document.getString("contact"))
                }
            }
    }

    override fun setupListeners() {
        binding.btnConfirm.setOnClickListener {
            val newPhoneNumber = binding.etNewPhoneNumber.text.toString().trim()
            val confirmNewPhoneNumber = binding.etConfirmNewPhoneNumber.text.toString().trim()

            if (newPhoneNumber.isNotEmpty() && newPhoneNumber == confirmNewPhoneNumber) {
                db.collection("users").document(userId).update("contact", newPhoneNumber)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Contact number updated successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error updating contact number: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "New phone numbers do not match or are empty", Toast.LENGTH_SHORT).show()
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