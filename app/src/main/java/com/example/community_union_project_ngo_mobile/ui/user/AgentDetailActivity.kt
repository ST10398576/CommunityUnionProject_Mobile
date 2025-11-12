package com.example.community_union_project_ngo_mobile.ui.user

import android.os.Bundle
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityAgentDetailBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.firestore.FirebaseFirestore

class AgentDetailActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityAgentDetailBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var agentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        agentId = intent.getStringExtra("AGENT_ID")!!

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        db.collection("users").document(agentId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val agentName = document.getString("fullName")
                    binding.btnCallAgent.text = "Call $agentName"
                    val agentDetails = "Agent is currently busy.\nAgent Operational Area: ${document.getString("location")}\nAgent Number: ${document.getString("contact")}"
                    binding.tvAgentDetails.text = agentDetails
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