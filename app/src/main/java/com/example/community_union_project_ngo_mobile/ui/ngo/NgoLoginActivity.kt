package com.example.community_union_project_ngo_mobile.ui.ngo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoLoginBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class NgoLoginActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityNgoLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoLoginBinding.inflate(layoutInflater)
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
            val ngoName = binding.etNgoName.text.toString().trim()
            val registrationNumber = binding.etRegistrationNumber.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (ngoName.isEmpty() || registrationNumber.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Replace with actual authentication logic (RoomDB / Firebase)
                val intent = Intent(this, NgoHomeActivity::class.java)
                intent.putExtra("NGO_NAME", ngoName)
                startActivity(intent)
            }
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, NgoRegisterActivity::class.java))
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}