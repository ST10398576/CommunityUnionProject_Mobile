package com.example.community_union_project_ngo_mobile.ui.ngo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoRegisterBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NgoRegisterActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityNgoRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // TODO: Implement UI setup
    }

    override fun setupListeners() {
        binding.btnRegister.setOnClickListener {
            val ngoName = binding.etNgoName.text.toString().trim()
            val registrationNumber = binding.etRegistrationNumber.text.toString().trim()
            val pboNumber = binding.etPboNumber.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            if (ngoName.isEmpty() || registrationNumber.isEmpty() || pboNumber.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = "$registrationNumber@cup.com"

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser!!.uid
                        val user = hashMapOf(
                            "ngoName" to ngoName,
                            "email" to email,
                            "registrationNumber" to registrationNumber,
                            "pboNumber" to pboNumber,
                            "role" to "ngo",
                            "isVerified" to false // Admins will need to verify this account
                        )

                        db.collection("users").document(userId)
                            .set(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Registration successful, pending verification", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, NgoLoginActivity::class.java)
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
            val intent = Intent(this, NgoLoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}