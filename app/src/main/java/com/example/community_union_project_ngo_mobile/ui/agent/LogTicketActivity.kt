package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityLogTicketBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LogTicketActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityLogTicketBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val ticketCategory = intent.getStringExtra("TICKET_CATEGORY")
        binding.tvTitle.text = ticketCategory

        val beneficiaries = mutableListOf<String>()
        db.collection("users").whereEqualTo("role", "beneficiary").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    beneficiaries.add(document.getString("fullName") ?: "")
                }
                val beneficiaryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, beneficiaries)
                beneficiaryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spnBeneficiaryName.adapter = beneficiaryAdapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting beneficiaries: ${exception.message}", Toast.LENGTH_SHORT).show()
            }


        val urgencyLevels = arrayOf(1, 2, 3, 4, 5)
        val urgencyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, urgencyLevels)
        urgencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnUrgencyLevel.adapter = urgencyAdapter
    }

    override fun setupListeners() {
        binding.btnConfirm.setOnClickListener {
            val ticket = hashMapOf(
                "beneficiaryName" to binding.spnBeneficiaryName.selectedItem.toString(),
                "category" to intent.getStringExtra("TICKET_CATEGORY"),
                "date" to binding.etDate.text.toString(),
                "deliveryLocation" to binding.etDeliveryLocation.text.toString(),
                "details" to binding.etDetails.text.toString(),
                "fieldAgentName" to auth.currentUser?.displayName,
                "howToHelp" to binding.etHowToHelp.text.toString(),
                "status" to "Pending",
                "urgency" to binding.spnUrgencyLevel.selectedItem.toString().toInt()
            )

            db.collection("tickets").add(ticket)
                .addOnSuccessListener {
                    Toast.makeText(this, "Ticket logged successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ListOfTicketsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error logging ticket: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // No observers needed
    }
}