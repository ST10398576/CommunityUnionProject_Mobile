package com.example.community_union_project_ngo_mobile.ui.user

import android.content.Intent
import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityUserLoginBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class UserLoginActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityUserLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // TODO: Implement UI setup
    }

    override fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            // TODO: Authenticate user
            val email = binding.etEmail.text.toString()
            val username = if (email.contains("@")) email.substringBefore('@') else email
            val intent = Intent(this, UserHomeActivity::class.java)
            intent.putExtra("USER_NAME", username)
            startActivity(intent)
        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, UserRegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}