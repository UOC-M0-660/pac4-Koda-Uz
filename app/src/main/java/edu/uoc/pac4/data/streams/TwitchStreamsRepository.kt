package edu.uoc.pac4.data.streams

import edu.uoc.pac4.data.TwitchDataSource

/**
 * Created by alex on 11/21/20.
 */

class TwitchStreamsRepository(
    private val twitchDataSource: TwitchDataSource
) : StreamsRepository {

    override suspend fun getStreams(cursor: String?): Pair<String?, List<Stream>> {
        val streamsResponse = twitchDataSource.getStreams(cursor)
        streamsResponse?.let {
            val streamsList = it.data.orEmpty()
            val newCursor = it.pagination?.cursor
            return Pair(newCursor, streamsList)
        }

        return Pair(null, emptyList())
    }

}