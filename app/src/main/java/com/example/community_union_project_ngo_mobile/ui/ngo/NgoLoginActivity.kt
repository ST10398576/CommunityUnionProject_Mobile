package com.example.community_union_project_ngo_mobile.ui.ngo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoLoginBinding

class NgoLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNgoLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNgoLogin.setOnClickListener {
            val email = binding.etNgoLoginEmail.text.toString().trim()
            val password = binding.etNgoLoginPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Replace with actual authentication logic (RoomDB / Firebase)
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                // Redirect to NGO dashboard
            }
        }

        binding.tvNgoRegister.setOnClickListener {
            startActivity(Intent(this, NgoRegisterActivity::class.java))
        }
    }
}
