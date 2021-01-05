package edu.uoc.pac4.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uoc.pac4.data.network.UnauthorizedException
import edu.uoc.pac4.data.oauth.AuthenticationRepository
import edu.uoc.pac4.data.user.User
import edu.uoc.pac4.data.user.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
        private val userRepository: UserRepository,
        private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    // Live Data
    val user = MutableLiveData<User>()
    val isLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()
    val isLoggedOut = MutableLiveData<Boolean>()

    // Retrieves user data from Twitch API
    fun getUserProfile() {
        isLoading.postValue(true)

        // Retrieve the Twitch User Profile using the API
        viewModelScope.launch {
            try {
                userRepository.getUser()?.let {
                    // Success :)
                    // Post user value
                    user.postValue(it)
                } ?: run {
                    // Error :(
                    showError.postValue(true)
                }

                // Hide Loading
                isLoading.postValue(false)
            } catch (t: UnauthorizedException) {
                // User is unauthorized
                logout()
                isLoggedOut.postValue(true)
            }
        }
    }

    // Updates User description using Twitch API
    fun updateUserDescription(description: String) {
        isLoading.postValue(true)

        // Update the Twitch User Description using the API
        viewModelScope.launch {
            try {
                userRepository.updateUser(description)?.let {
                    // Success :)
                    // Post user value
                    user.postValue(it)
                } ?: run {
                    // Error :(
                    showError.postValue(true)
                }

                // Hide Loading
                isLoading.postValue(false)
            } catch (t: UnauthorizedException) {
                // User is unauthorized
                logout()
                isLoggedOut.postValue(true)
            }
        }
    }

    fun logout() {
        // Clear local session data
        viewModelScope.launch { authenticationRepository.logout() }
    }
}