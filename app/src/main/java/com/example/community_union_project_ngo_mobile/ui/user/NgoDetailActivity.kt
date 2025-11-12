package com.example.community_union_project_ngo_mobile.ui.user

import android.os.Bundle
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoDetailBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.firestore.FirebaseFirestore

class NgoDetailActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityNgoDetailBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var ngoId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        ngoId = intent.getStringExtra("NGO_ID")!!

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        db.collection("users").document(ngoId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.tvNgoName.text = document.getString("ngoName")
                    binding.tvCommunityAssistance.text = document.getString("communityAssistance")
                    binding.tvPrograms.text = document.getString("programs")
                    binding.tvWesternCapePresence.text = document.getString("westernCapePresence")
                    binding.tvContact.text = document.getString("contactInformation")
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting document: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // No observers needed
    }
}