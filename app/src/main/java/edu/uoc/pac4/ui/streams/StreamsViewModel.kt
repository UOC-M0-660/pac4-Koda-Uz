package edu.uoc.pac4.ui.streams

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uoc.pac4.data.network.UnauthorizedException
import edu.uoc.pac4.data.oauth.AuthenticationRepository
import edu.uoc.pac4.data.streams.Stream
import edu.uoc.pac4.data.streams.StreamsRepository
import kotlinx.coroutines.launch

private const val TAG = "StreamsViewModel"

class StreamsViewModel(
        private val streamsRepository: StreamsRepository,
        private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    // Live Data
    val streamList = MutableLiveData<List<Stream>>()
    val isRefreshing = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()
    val isLoggedOut = MutableLiveData<Boolean>()

    // Cursor
    private var cursor: String? = null

    // Retrieves streams from the repository
    fun getStreams() {
        Log.d(TAG, "Requesting streams with cursor $cursor")

        // Posts Loading Status
        isRefreshing.postValue(true)

        // Get Twitch Streams
        viewModelScope.launch {
            try {
                // Gets streams from repository
                val streamsPair = streamsRepository.getStreams(cursor)
                val streams = streamsPair.second
                if (streams.isNotEmpty()) {
                    // Success :)
                    Log.d("StreamsActivity", "Got Streams: $streamsPair")

                    // Posts Streams received
                    streamList.postValue(streams)

                    // Save cursor for next request
                    cursor = streamsPair.first
                }
                else {
                    // Error :(

                    // Post error value
                    showError.postValue(true)
                }

                // Posts Loading Status
                isRefreshing.postValue(false)

            } catch (t: UnauthorizedException) {
                Log.w(TAG, "Unauthorized Error getting streams", t)
                // Clear local access token
                viewModelScope.launch { authenticationRepository.logout() }
                // Posts logged out value
                isLoggedOut.postValue(true)
            }
        }
    }

    // Returns true if cursor is null
    // Used in scroll listener
    fun isCursorNull(): Boolean {
        return cursor == null
    }
}