package edu.uoc.pac4.data.user

import edu.uoc.pac4.data.TwitchDataSource

/**
 * Created by alex on 11/21/20.
 */

class TwitchUserRepository(
    private val twitchDataSource: TwitchDataSource
) : UserRepository {

    override suspend fun getUser(): User? {
        return twitchDataSource.getUser()
    }

    override suspend fun updateUser(description: String): User? {
        return twitchDataSource.updateUserDescription(description)
    }
}