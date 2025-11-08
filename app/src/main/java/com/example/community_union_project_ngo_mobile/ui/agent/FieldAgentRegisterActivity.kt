package com.example.community_union_project_ngo_mobile.ui.agent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityFieldAgentRegisterBinding

class FieldAgentRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFieldAgentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFieldAgentRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            // TODO: Register field agent
        }

        binding.btnBack.setOnClickListener { finish() }
    }
}
