package com.fls.self_care_assistant.repositories

import android.content.SharedPreferences

class TokenRepository {
    private lateinit var sharedPreferences: SharedPreferences

    fun setupSharedPrefs(sharedPrefs: SharedPreferences) {
        sharedPreferences = sharedPrefs
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, null)
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    companion object {
        val instance: TokenRepository by lazy { Repo.Instance }
        private const val TOKEN_KEY = "com.fls.self_care_assistant.token"
    }

    private object Repo {
        val Instance = TokenRepository()
    }
}