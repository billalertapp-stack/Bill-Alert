package uk.ac.tees.mad.billalert.core

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest


object SupabaseClientProvider {

    val client = createSupabaseClient(
        supabaseUrl = "https://vwlqfdoqarhpppykwbdy.supabase.co",   // replace before build
        supabaseKey = "your-anon-key"                            // replace before build
    ) {
        install(Auth)
        install(Postgrest)
    }
}