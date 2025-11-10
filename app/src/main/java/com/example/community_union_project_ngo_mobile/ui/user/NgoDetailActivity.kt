package com.example.community_union_project_ngo_mobile.ui.user

import android.os.Bundle
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoDetailBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

class NgoDetailActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityNgoDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val ngoName = intent.getStringExtra("NGO_NAME")
        binding.tvNgoName.text = ngoName

        val communityAssistance: String
        val programs: String
        val westernCapePresence: String
        val contact: String

        when (ngoName) {
            "Qhubeka Charity Foundation" -> {
                communityAssistance = "Provides bicycles to individuals, enhancing access to education, healthcare, and employment opportunities."
                programs = "Work-to-Earn, Learn-to-Earn, and Sport-to-Earn initiatives where participants earn bicycles through community service or academic achievements."
                westernCapePresence = "Active in areas like Khayelitsha, facilitating mobility for schoolchildren and micro-enterprises."
                contact = "Email: info@qhubeka.org | Phone: +27 83 325 1344"
            }
            // Add other NGOs here
            else -> {
                communityAssistance = "Details not available."
                programs = "Details not available."
                westernCapePresence = "Details not available."
                contact = "Details not available."
            }
        }
        binding.tvCommunityAssistance.text = communityAssistance
        binding.tvPrograms.text = programs
        binding.tvWesternCapePresence.text = westernCapePresence
        binding.tvContact.text = contact
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}