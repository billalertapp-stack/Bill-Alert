package uk.ac.tees.mad.billalert.core

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest


object SupabaseClientProvider {

    val client = createSupabaseClient(
        supabaseUrl = "https://vwlqfdoqarhpppykwbdy.supabase.co",
        supabaseKey = "sb_publishable_6pw0xzaVw3XmpzQlOSU4EA_yVwJGVj6"
    ) {
        install(Auth)
        install(Postgrest)
    }
}