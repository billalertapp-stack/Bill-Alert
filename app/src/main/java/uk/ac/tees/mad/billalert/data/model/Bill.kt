package uk.ac.tees.mad.billalert.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Bill(
    val id: String = "",
    @SerialName("user_id")
    val userId: String = "",
    val title: String = "",
    val category: BillCategory = BillCategory.OTHER,
    val amount: Double = 0.0,
    @SerialName("due_date")
    val dueDate: String = "",          // ISO-8601 date: "2025-07-15"
    @SerialName("reminder_days")
    val reminderDays: Int = 3,
    @SerialName("is_paid")
    val isPaid: Boolean = false,
    @SerialName("created_at")
    val createdAt: String = ""
)

@Serializable
enum class BillCategory {
    @SerialName("Electricity") ELECTRICITY,
    @SerialName("Internet")    INTERNET,
    @SerialName("Rent")        RENT,
    @SerialName("Water")       WATER,
    @SerialName("Other")       OTHER;

    fun displayName() = name.lowercase().replaceFirstChar { it.uppercase() }

    fun emoji() = when (this) {
        ELECTRICITY -> "⚡"
        INTERNET    -> "🌐"
        RENT        -> "🏠"
        WATER       -> "💧"
        OTHER       -> "📄"
    }
}