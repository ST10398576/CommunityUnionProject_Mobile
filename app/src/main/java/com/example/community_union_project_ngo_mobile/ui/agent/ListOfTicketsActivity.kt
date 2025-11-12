package com.example.community_union_project_ngo_mobile.ui.agent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.community_union_project_ngo_mobile.R
import com.example.community_union_project_ngo_mobile.databinding.ActivityListOfTicketsBinding
import com.example.community_union_project_ngo_mobile.ui.common.BaseAuthActivity

data class Ticket(
    val ticketNumber: Int,
    val ticketType: String,
    val beneficiary: String,
    val fieldAgent: String,
    val date: String,
    val urgency: Int,
    val location: String,
    val status: String,
    val description: String
)

class ListOfTicketsActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityListOfTicketsBinding

    // Placeholder for ticket data
    private val tickets = listOf(
        Ticket(57, "Food", "Vincent Abraham", "David Sunday", "30 January 2025", 2, "Cape Town", "Pending", "Delivery of Food *****"),
        Ticket(56, "Housing", "Ethan Cole", "Megan Cross", "16 March 2025", 3, "Paarl", "Resolved", ""),
        Ticket(55, "Employment", "Ashley Pierce", "Nathan Carter", "29 July 2025", 2, "Worcester", "In Progress", ""),
        Ticket(54, "Transport", "Conner James", "David Sunday", "10 January 2025", 1, "Somerset West", "Pending", "")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOfTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
        setupObservers()
    }

    override fun setupUI() {
        val userRole = intent.getStringExtra("USER_ROLE")

        tickets.forEach { ticket ->
            val ticketView = createTicketView(ticket, userRole)
            binding.llTicketList.addView(ticketView)
        }

        if (userRole == "NGO" || userRole == "ADMIN") {
            binding.llButtons.visibility = View.GONE
            val backButton = binding.btnHomePage
            backButton.text = "Back"
            backButton.setOnClickListener { finish() }
        }
    }

    private fun createTicketView(ticket: Ticket, userRole: String?): LinearLayout {
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
            if (userRole == "AGENT") {
                setOnClickListener {
                    val intent = Intent(this@ListOfTicketsActivity, UpdateTicketActivity::class.java)
                    intent.putExtra("TICKET_TYPE", ticket.ticketType)
                    startActivity(intent)
                }
            }
        }

        val titleTextView = TextView(this).apply {
            text = "${ticket.ticketType} Ticket No ${ticket.ticketNumber}"
            textSize = 18f
            setTextColor(resources.getColor(android.R.color.black))
        }

        val detailsTextView = TextView(this).apply {
            text = "Beneficiary: ${ticket.beneficiary}\n"
            append("Field Agent: ${ticket.fieldAgent}\n")
            append("Date: ${ticket.date}\n")
            append("Urgency: Level ${ticket.urgency}\n")
            append("Location: ${ticket.location}\n")
            append("Status: ${ticket.status}\n")
            append("Description: ${ticket.description}")
            setTextColor(resources.getColor(android.R.color.black))
        }

        linearLayout.addView(titleTextView)
        linearLayout.addView(detailsTextView)

        return linearLayout
    }

    override fun setupListeners() {
        val userRole = intent.getStringExtra("USER_ROLE")
        if (userRole == "AGENT") {
            binding.btnHomePage.setOnClickListener {
                finish()
            }

            binding.btnAddTickets.setOnClickListener {
                val intent = Intent(this, TicketLogActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun setupObservers() {
        // TODO: Implement observers
    }
}