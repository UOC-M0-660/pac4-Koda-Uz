package edu.uoc.pac4.data

import android.content.SharedPreferences
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class SessionManagerDataSource (private val sharedPreferences: SharedPreferences) {

    private val accessTokenKey = "accessTokeKey"
    private val refreshTokenKey = "refreshTokenKey"

    fun isUserAvailable(): Boolean {
        return getAccessToken() != null
    }

    private fun getAccessToken(): String? {
        return sharedPreferences.getString(accessTokenKey, null)
    }

    fun saveAccessToken(accessToken: String) {
        val editor = sharedPreferences.edit()
        editor.putString(accessTokenKey, accessToken)
        editor.apply()
    }

    fun clearAccessToken() {
        val editor = sharedPreferences.edit()
        editor.remove(accessTokenKey)
        editor.apply()
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString(refreshTokenKey, null)
    }

    fun saveRefreshToken(refreshToken: String) {
        val editor = sharedPreferences.edit()
        editor.putString(refreshTokenKey, refreshToken)
        editor.apply()
    }

    fun clearRefreshToken() {
        val editor = sharedPreferences.edit()
        editor.remove(refreshTokenKey)
        editor.apply()
    }
}