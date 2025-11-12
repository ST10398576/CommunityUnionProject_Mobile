package com.example.community_union_project_ngo_mobile.ui.ngo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.databinding.ActivityTerminateContractBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TerminateContractActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityTerminateContractBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTerminateContractBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userId = auth.currentUser!!.uid

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        // No specific UI setup needed
    }

    override fun setupListeners() {
        binding.btnSendRequest.setOnClickListener {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val ngoName = document.getString("ngoName")
                        val request = hashMapOf(
                            "ngoId" to userId,
                            "ngoName" to ngoName,
                            "status" to "pending"
                        )
                        db.collection("terminationRequests").document(userId).set(request)
                            .addOnSuccessListener {
                                val intent = Intent(this, TerminationRequestSentActivity::class.java)
                                startActivity(intent)
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error sending request: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
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