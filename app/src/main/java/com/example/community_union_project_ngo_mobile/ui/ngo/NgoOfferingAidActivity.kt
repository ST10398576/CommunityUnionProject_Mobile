package com.example.community_union_project_ngo_mobile.ui.ngo

import android.os.Bundle
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityNgoOfferingAidBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NgoOfferingAidActivity : BaseAuthActivity() {

    private lateinit var binding: ActivityNgoOfferingAidBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoOfferingAidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userId = auth.currentUser!!.uid

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.etNgoName.setText(document.getString("ngoName"))
                    binding.etCommunityAssistance.setText(document.getString("communityAssistance"))
                    binding.etPrograms.setText(document.getString("programs"))
                    binding.etWesternCapePresence.setText(document.getString("westernCapePresence"))
                    binding.etContactInformation.setText(document.getString("contactInformation"))
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting document: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun setupListeners() {
        binding.btnConfirm.setOnClickListener {
            val aidDetails = hashMapOf(
                "communityAssistance" to binding.etCommunityAssistance.text.toString(),
                "programs" to binding.etPrograms.text.toString(),
                "westernCapePresence" to binding.etWesternCapePresence.text.toString(),
                "contactInformation" to binding.etContactInformation.text.toString()
            )

            db.collection("users").document(userId).update(aidDetails as Map<String, Any>)
                .addOnSuccessListener {
                    Toast.makeText(this, "Aid details updated successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error updating details: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
        // TODO: Implement observers if needed
    }
}
