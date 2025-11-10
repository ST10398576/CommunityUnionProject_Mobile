package com.example.community_union_project_ngo_mobile.ui.ngo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoHomeBinding

class NgoHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNgoHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ngoName = intent.getStringExtra("NGO_NAME")
        binding.tvWelcome.text = "Welcome\n${ngoName}"
    }
}