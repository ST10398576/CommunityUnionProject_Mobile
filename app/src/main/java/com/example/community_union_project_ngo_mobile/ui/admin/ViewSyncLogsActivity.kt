package com.example.community_union_project_ngo_mobile.ui.admin

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityViewSyncLogsBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity
import com.google.firebase.firestore.FirebaseFirestore

data class SyncLog(val agentName: String, val region: String, val status: String, val lastSync: String)

class ViewSyncLogsActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityViewSyncLogsBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewSyncLogsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        db.collection("users").whereEqualTo("role", "agent").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    // TODO: Implement actual online/offline status logic
                    val log = SyncLog(
                        document.getString("fullName") ?: "",
                        document.getString("location") ?: "",
                        "Offline", // Placeholder
                        "12:43 PM" // Placeholder
                    )
                    val logView = createSyncLogView(log)
                    binding.llSyncLogList.addView(logView)
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting documents: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun createSyncLogView(log: SyncLog): LinearLayout {
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

        val agentTextView = TextView(this).apply {
            text = "Assigned Agent: ${log.agentName}"
            textSize = 18f
            setTextColor(Color.BLACK)
        }

        val regionTextView = TextView(this).apply {
            text = "Region: ${log.region}"
            setTextColor(Color.BLACK)
        }

        val statusTextView = TextView(this).apply {
            text = "Status: ${log.status}"
            setTextColor(if (log.status == "Online") Color.GREEN else Color.RED)
        }

        val syncTextView = TextView(this).apply {
            text = if (log.status == "Online") log.lastSync else "Not Synced: Last Sync - ${log.lastSync}"
            setTextColor(Color.BLACK)
        }

        linearLayout.addView(agentTextView)
        linearLayout.addView(regionTextView)
        linearLayout.addView(statusTextView)
        linearLayout.addView(syncTextView)

        return linearLayout
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