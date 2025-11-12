package com.example.community_union_project_ngo_mobile.ui.admin

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityViewSyncLogsBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

data class SyncLog(val agentName: String, val region: String, val status: String, val lastSync: String)

class ViewSyncLogsActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityViewSyncLogsBinding

    // Placeholder for sync log data
    private val logs = listOf(
        SyncLog("Sarah Khan", "Western Cape", "Offline", "12:43 PM"),
        SyncLog("David Sunday", "Cape Town", "Online", "Synced"),
        SyncLog("Michelle Lopez", "Paarl", "Offline", "11:42 AM")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewSyncLogsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        logs.forEach { log ->
            val logView = createSyncLogView(log)
            binding.llSyncLogList.addView(logView)
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
        // TODO: Implement observers
    }
}