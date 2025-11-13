package com.example.community_union_project_ngo_mobile.ui.admin

import android.os.Bundle
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityVerifyFieldAgentBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class VerifyFieldAgentActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityVerifyFieldAgentBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyFieldAgentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        userId = intent.getStringExtra("USER_ID")!!

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.tvFullName.text = document.getString("fullName")
                    binding.tvAssociatedNgo.text = document.getString("associatedNgo")
                    binding.tvAssociatedNgoId.text = document.getString("ngoAgentId")
                    binding.tvLocation.text = document.getString("location")
                    // TODO: Implement document download logic
                    binding.tvEmployment.text = "employment_document.docx"
                    binding.tvAgentPhoto.text = "agent_photo.png"
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting document: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun setupListeners() {
        binding.btnAccept.setOnClickListener {
            db.collection("users").document(userId).update("isVerified", true)
                .addOnSuccessListener {
                    // TODO: Send acceptance email to the user
                    Toast.makeText(this, "Field Agent Verified", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error updating document: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnReject.setOnClickListener {
            // First, delete the user's Firestore document
            db.collection("users").document(userId).delete()
                .addOnSuccessListener {
                    // IMPORTANT: The user's authentication record must be deleted manually from the Firebase Console.
                    // The client SDK does not have permission to delete other users for security reasons.
                    // TODO: Send rejection email
                    Toast.makeText(this, "Field Agent Rejected. Remember to delete their auth record.", Toast.LENGTH_LONG).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error deleting document: ${e.message}", Toast.LENGTH_SHORT).show()
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