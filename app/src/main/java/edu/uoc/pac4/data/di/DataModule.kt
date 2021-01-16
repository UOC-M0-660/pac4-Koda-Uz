package edu.uoc.pac4.data.di

import android.content.Context
import android.content.SharedPreferences
import edu.uoc.pac4.data.sources.LocalDataSource
import edu.uoc.pac4.data.sources.TwitchDataSource
import edu.uoc.pac4.data.network.Network
import edu.uoc.pac4.data.oauth.AuthenticationRepository
import edu.uoc.pac4.data.oauth.OAuthAuthenticationRepository
import edu.uoc.pac4.data.streams.StreamsRepository
import edu.uoc.pac4.data.streams.TwitchStreamsRepository
import edu.uoc.pac4.data.user.TwitchUserRepository
import edu.uoc.pac4.data.user.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by alex on 11/21/20.
 */

private const val sharedPreferencesName = "sessionPreferences"

val dataModule = module {
    // Shared preferences
    single<SharedPreferences> {
        androidContext().getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
    }

    // HttpClient
    single { Network.createHttpClient(get()) }

    // DataSources
    single { TwitchDataSource(get()) }
    single { LocalDataSource(get()) }

    // Streams
    single<StreamsRepository> { TwitchStreamsRepository(get()) }

    // Authentication
    single<AuthenticationRepository> { OAuthAuthenticationRepository(get(), get()) }

    // User
    single<UserRepository> { TwitchUserRepository(get()) }
}