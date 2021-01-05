package edu.uoc.pac4.ui.login.oauth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uoc.pac4.data.oauth.AuthenticationRepository
import kotlinx.coroutines.launch

class OAuthViewModel(
        private val authenticationRepository: AuthenticationRepository
): ViewModel() {

    // Live Data
    val loginResult = MutableLiveData<Boolean>()

    // Login Function
    fun login(authorizationCode: String) {
        viewModelScope.launch {
            loginResult.postValue(authenticationRepository.login(authorizationCode))
        }
    }
}