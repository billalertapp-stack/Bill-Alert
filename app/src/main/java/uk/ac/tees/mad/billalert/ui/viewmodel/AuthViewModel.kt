package uk.ac.tees.mad.billalert.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.billalert.core.SupabaseClientProvider

sealed class AuthUiState {
    data object Idle    : AuthUiState()
    data object Loading : AuthUiState()
    data object Success : AuthUiState()
    data class  Error(val message: String) : AuthUiState()
}

class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState

    fun signIn(email: String, password: String) {
        if (!validate(email, password)) return
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            try {
                SupabaseClientProvider.client.auth.signInWith(Email) {
                    this.email    = email.trim()
                    this.password = password
                }
                _uiState.value = AuthUiState.Success
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message ?: "Sign-in failed")
            }
        }
    }

    fun register(email: String, password: String) {
        if (!validate(email, password)) return
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            try {
                SupabaseClientProvider.client.auth.signUpWith(Email) {
                    this.email    = email.trim()
                    this.password = password
                }
                _uiState.value = AuthUiState.Success
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message ?: "Registration failed")
            }
        }
    }

    private fun validate(email: String, password: String): Boolean {
        return when {
            email.isBlank()      -> { _uiState.value = AuthUiState.Error("Email cannot be empty"); false }
            password.length < 6  -> { _uiState.value = AuthUiState.Error("Password must be at least 6 characters"); false }
            else                 -> true
        }
    }
}