package uk.ac.tees.mad.billalert.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.billalert.core.SupabaseClientProvider

sealed class SplashUiState {
    data object Loading        : SplashUiState()
    data object Authenticated  : SplashUiState()
    data object Unauthenticated: SplashUiState()
}

class SplashViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            delay(1500L) // allow splash animation to play
            try {
                val session = SupabaseClientProvider.client.auth.currentSessionOrNull()
                _uiState.value = if (session != null) SplashUiState.Authenticated
                else SplashUiState.Unauthenticated
            } catch (e: Exception) {
                _uiState.value = SplashUiState.Unauthenticated
            }
        }
    }
}