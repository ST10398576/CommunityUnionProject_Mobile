package com.example.community_union_project_ngo_mobile.ui.ngo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoRegisterBinding

class NgoRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNgoRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNgoRegister.setOnClickListener {
            val name = binding.etNgoName.text.toString().trim()
            val email = binding.etNgoEmail.text.toString().trim()
            val phone = binding.etNgoPhone.text.toString().trim()
            val address = binding.etNgoAddress.text.toString().trim()
            val password = binding.etNgoPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Replace this with RoomDB or Firebase registration logic
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()

                // Redirect to NGO Login page
                startActivity(Intent(this, NgoLoginActivity::class.java))
                finish()
            }
        }

        binding.tvNgoLogin.setOnClickListener {
            startActivity(Intent(this, NgoLoginActivity::class.java))
        }
    }
}
