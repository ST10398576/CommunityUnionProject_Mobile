package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityFieldAgentLoginBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FieldAgentLoginActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityFieldAgentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFieldAgentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // No specific UI setup needed
    }

    override fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            val agentId = binding.etAgentId.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (agentId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = "$agentId@cup.com"

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser!!.uid
                        db.collection("users").document(userId).get()
                            .addOnSuccessListener { document ->
                                if (document != null) {
                                    val isVerified = document.getBoolean("isVerified")
                                    if (isVerified == true) {
                                        val agentName = document.getString("fullName")
                                        val intent = Intent(this, FieldAgentHomeActivity::class.java)
                                        intent.putExtra("AGENT_NAME", agentName)
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        Toast.makeText(this, "Account not verified", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    Toast.makeText(this, "User details not found", Toast.LENGTH_SHORT).show()
                                }
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error getting user details: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, FieldAgentRegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}