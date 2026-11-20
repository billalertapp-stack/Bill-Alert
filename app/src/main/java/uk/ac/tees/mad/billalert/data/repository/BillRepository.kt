package uk.ac.tees.mad.billalert.data.repository

import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uk.ac.tees.mad.billalert.core.Constants
import uk.ac.tees.mad.billalert.core.SupabaseClientProvider
import uk.ac.tees.mad.billalert.data.model.Bill

class BillRepository {

    private val client = SupabaseClientProvider.client

    private fun currentUserId(): String =
        client.auth.currentUserOrNull()?.id ?: error("Not authenticated")

    // ── Fetch all bills for current user ──────────────────────────────────────
    suspend fun getBills(): List<Bill> {
        return client.postgrest[Constants.TABLE_BILLS]
            .select {
                filter { eq("user_id", currentUserId()) }
            }
            .decodeList()
    }

    // ── Insert a new bill ──────────────────────────────────────────────────────
    suspend fun insertBill(bill: Bill) {
        val withUser = bill.copy(userId = currentUserId())
        client.postgrest[Constants.TABLE_BILLS].insert(withUser)
    }

    // ── Update an existing bill ────────────────────────────────────────────────
    suspend fun updateBill(bill: Bill) {
        client.postgrest[Constants.TABLE_BILLS]
            .update(bill) {
                filter { eq("id", bill.id) }
            }
    }

    // ── Delete a bill by id ────────────────────────────────────────────────────
    suspend fun deleteBill(billId: String) {
        client.postgrest[Constants.TABLE_BILLS]
            .delete {
                filter { eq("id", billId) }
            }
    }

    // ── Toggle paid status ─────────────────────────────────────────────────────
    suspend fun markPaid(billId: String, isPaid: Boolean) {
        client.postgrest[Constants.TABLE_BILLS]
            .update({ set("is_paid", isPaid) }) {
                filter { eq("id", billId) }
            }
    }

    // ── Real-time changes as a Flow ────────────────────────────────────────────
    fun billChangesFlow(): Flow<Unit> = flow {
        val channel = client.realtime.channel("bills_channel")
        channel.postgresChangeFlow<PostgresAction>(schema = "public") {
            table = Constants.TABLE_BILLS
        }.collect { emit(Unit) }
        channel.subscribe()
    }
}