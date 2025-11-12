package com.example.community_union_project_ngo_mobile.ui.admin

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityTerminationRequestsBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.firestore.FirebaseFirestore

data class TerminationRequest(val id: String, val ngoId: String, val ngoName: String, val status: String)

class TerminationRequestsActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityTerminationRequestsBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTerminationRequestsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        db.collection("terminationRequests").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val request = TerminationRequest(
                        document.id,
                        document.getString("ngoId") ?: "",
                        document.getString("ngoName") ?: "",
                        document.getString("status") ?: ""
                    )
                    val requestView = createRequestView(request)
                    binding.llTerminationRequests.addView(requestView)
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting documents: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun createRequestView(request: TerminationRequest): LinearLayout {
        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 16, 0, 0)
            }
            setBackgroundResource(R.drawable.edit_text_background)
            setPadding(16, 16, 16, 16)
        }

        val ngoNameTextView = TextView(this).apply {
            text = "NGO: ${request.ngoName}"
            textSize = 18f
        }

        val statusTextView = TextView(this).apply {
            text = "Status: ${request.status}"
        }

        val approveButton = Button(this).apply {
            text = "Approve"
            setOnClickListener {
                approveTermination(request)
            }
        }

        val denyButton = Button(this).apply {
            text = "Deny"
            setOnClickListener {
                updateRequestStatus(request.id, "denied")
            }
        }

        linearLayout.addView(ngoNameTextView)
        linearLayout.addView(statusTextView)
        linearLayout.addView(approveButton)
        linearLayout.addView(denyButton)

        return linearLayout
    }

    private fun approveTermination(request: TerminationRequest) {
        // First, delete the user document
        db.collection("users").document(request.ngoId).delete()
            .addOnSuccessListener {
                // Then, delete the termination request document
                db.collection("terminationRequests").document(request.id).delete()
                    .addOnSuccessListener {
                        Toast.makeText(this, "NGO termination approved and data wiped.", Toast.LENGTH_SHORT).show()
                        // Refresh the list
                        binding.llTerminationRequests.removeAllViews()
                        setupUI()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error deleting termination request: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error deleting NGO data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateRequestStatus(requestId: String, newStatus: String) {
        db.collection("terminationRequests").document(requestId).update("status", newStatus)
            .addOnSuccessListener {
                Toast.makeText(this, "Request status updated to $newStatus", Toast.LENGTH_SHORT).show()
                // Refresh the list
                binding.llTerminationRequests.removeAllViews()
                setupUI()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error updating status: ${e.message}", Toast.LENGTH_SHORT).show()
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