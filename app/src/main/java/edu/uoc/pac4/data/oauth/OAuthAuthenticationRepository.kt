package edu.uoc.pac4.data.oauth

import edu.uoc.pac4.data.sources.LocalDataSource
import edu.uoc.pac4.data.TwitchDataSource

/**
 * Created by alex on 11/21/20.
 */
class OAuthAuthenticationRepository(
        private val twitchDataSource: TwitchDataSource,
        private val localDataSource: LocalDataSource
) : AuthenticationRepository {

    override suspend fun isUserAvailable(): Boolean {
        return localDataSource.isUserAvailable()
    }

    override suspend fun login(authorizationCode: String): Boolean {
        twitchDataSource.getTokens(authorizationCode)?.let { response ->
            // Success :)

            localDataSource.saveAccessToken(response.accessToken)
            response.refreshToken?.let {
                localDataSource.saveRefreshToken(it)
            }
            return true
        } ?: run {
            // Failure :(

            return false
        }
    }

    override suspend fun logout() {
        localDataSource.clearAccessToken()
        localDataSource.clearRefreshToken()
    }
}