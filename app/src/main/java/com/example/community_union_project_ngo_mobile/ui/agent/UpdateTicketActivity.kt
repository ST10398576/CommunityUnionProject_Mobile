package com.example.community_union_project_ngo_mobile.ui.agent

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityUpdateTicketBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.firestore.FirebaseFirestore

class UpdateTicketActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityUpdateTicketBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var ticketId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        ticketId = intent.getStringExtra("TICKET_ID")!!

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
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

        val urgencyLevels = arrayOf(1, 2, 3, 4, 5)
        val urgencyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, urgencyLevels)
        urgencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnUrgencyLevel.adapter = urgencyAdapter

        val statuses = arrayOf("Pending", "In Progress", "Resolved")
        val statusAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statuses)
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnStatus.adapter = statusAdapter

        db.collection("tickets").document(ticketId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.tvTitle.text = document.getString("category")
                    binding.etBeneficiaryName.setText(document.getString("beneficiaryName"))
                    binding.etFieldAgent.setText(document.getString("fieldAgentName"))
                    binding.etDate.setText(document.getString("date"))
                    binding.spnUrgencyLevel.setSelection(urgencyAdapter.getPosition((document.getLong("urgency") ?: 1).toInt()))
                    binding.etDeliveryLocation.setText(document.getString("deliveryLocation"))
                    binding.etItemsNeeded.setText(document.getString("itemsNeeded"))
                    binding.etHowToHelp.setText(document.getString("howToHelp"))
                    binding.spnStatus.setSelection(statusAdapter.getPosition(document.getString("status")))
                }
            }
    }

    override fun setupListeners() {
        binding.btnUpdate.setOnClickListener {
            val updatedTicket = hashMapOf(
                "beneficiaryName" to binding.etBeneficiaryName.text.toString(),
                "fieldAgentName" to binding.etFieldAgent.text.toString(),
                "date" to binding.etDate.text.toString(),
                "urgency" to binding.spnUrgencyLevel.selectedItem.toString().toInt(),
                "deliveryLocation" to binding.etDeliveryLocation.text.toString(),
                "itemsNeeded" to binding.etItemsNeeded.text.toString(),
                "howToHelp" to binding.etHowToHelp.text.toString(),
                "status" to binding.spnStatus.selectedItem.toString()
            )

            db.collection("tickets").document(ticketId).update(updatedTicket as Map<String, Any>)
                .addOnSuccessListener {
                    Toast.makeText(this, "Ticket updated successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error updating ticket: ${e.message}", Toast.LENGTH_SHORT).show()
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