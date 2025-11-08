package com.example.community_union_project_ngo_mobile.ui.admin


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityAdminLoginBinding

class AdminLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdminLogin.setOnClickListener {
            val adminId = binding.etAdminId.text.toString().trim()
            val adminPassword = binding.etAdminPassword.text.toString().trim()

            if (adminId.isEmpty() || adminPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Replace with your admin authentication logic
                if (adminId == "admin001" && adminPassword == "password123") {
                    Toast.makeText(this, "Admin Login Successful", Toast.LENGTH_SHORT).show()
                    // Navigate to admin dashboard (to be implemented)
                } else {
                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnAdminBack.setOnClickListener { finish() }
    }
}
