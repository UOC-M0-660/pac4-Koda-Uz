package edu.uoc.pac4.data.oauth

import edu.uoc.pac4.data.SessionManagerDataSource
import edu.uoc.pac4.data.TwitchDataSource

/**
 * Created by alex on 11/21/20.
 */
class OAuthAuthenticationRepository(
        private val twitchDataSource: TwitchDataSource,
        private val sessionManagerDataSource: SessionManagerDataSource
) : AuthenticationRepository {

    override suspend fun isUserAvailable(): Boolean {
        return sessionManagerDataSource.isUserAvailable()
    }

    override suspend fun login(authorizationCode: String): Boolean {
        twitchDataSource.getTokens(authorizationCode)?.let { response ->
            // Success :)

            sessionManagerDataSource.saveAccessToken(response.accessToken)
            response.refreshToken?.let {
                sessionManagerDataSource.saveRefreshToken(it)
            }
            return true
        } ?: run {
            // Failure :(

            return false
        }
    }

    override suspend fun logout() {
        sessionManagerDataSource.clearAccessToken()
        sessionManagerDataSource.clearRefreshToken()
    }
}