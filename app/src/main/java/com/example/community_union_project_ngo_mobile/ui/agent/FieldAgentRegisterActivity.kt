package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityFieldAgentRegisterBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FieldAgentRegisterActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityFieldAgentRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFieldAgentRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val ngos = arrayOf("Gift of the Givers", "Food Forward SA", "The Lunch Box Foundation")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ngos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnAssociatedNgo.adapter = adapter
    }

    override fun setupListeners() {
        binding.btnRegister.setOnClickListener {
            val fullName = binding.etName.text.toString().trim()
            val contact = binding.etContact.text.toString().trim()
            val associatedNgo = binding.spnAssociatedNgo.selectedItem.toString()
            val ngoAgentId = binding.etNgoAgentId.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val location = binding.spnLocation.selectedItem.toString()

            if (fullName.isEmpty() || contact.isEmpty() || ngoAgentId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // For Field Agent registration, we can use the NGO Agent ID as the email
            val email = "$ngoAgentId@cup.com"

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser!!.uid
                        val user = hashMapOf(
                            "fullName" to fullName,
                            "email" to email,
                            "contact" to contact,
                            "associatedNgo" to associatedNgo,
                            "ngoAgentId" to ngoAgentId,
                            "location" to location,
                            "role" to "agent",
                            "isVerified" to true // Auto-verify
                        )

                        db.collection("users").document(userId)
                            .set(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, FieldAgentLoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error saving user details: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, FieldAgentLoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}