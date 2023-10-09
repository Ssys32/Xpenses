package com.example.xpenses.mainModule.model

import com.example.xpenses.common.entities.FirebaseUser
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val auth: FakeAuthentication,
    private val dataStore: DataStoreMain
) {

    fun getUser(callback: (FirebaseUser?) -> Unit) {
        /*callback(FirebaseUser("sys32",
            Uri.parse("https://1000marcas.net/wp-content/uploads/2020/01/Logo-Android.png"),
            "Sys32"))*/
        auth.getFirebaseUser { callback(it) }
    }

    fun resume() = auth.resume()
    fun pause() = auth.pause()

    suspend fun fetchInitialPreferences() = dataStore.fetchInitialPreferences()

    suspend fun updateLastDestination(lastDestination: String) {
        dataStore.updateLastDestination(lastDestination)
    }

}