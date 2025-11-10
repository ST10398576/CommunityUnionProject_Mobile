package com.example.community_union_project_ngo_mobile.ui.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityAdminLoginBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class AdminLoginActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityAdminLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // TODO: Implement UI setup
    }

    override fun setupListeners() {
        binding.btnAdminLogin.setOnClickListener {
            val adminId = binding.etAdminId.text.toString().trim()
            val adminPassword = binding.etAdminPassword.text.toString().trim()

            if (adminId.isEmpty() || adminPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Replace with your admin authentication logic
                if (adminId == "admin001" && adminPassword == "password123") {
                    val intent = Intent(this, AdminHomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnAdminBack.setOnClickListener { finish() }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}