package com.example.xpenses.mainModule.model

import com.example.xpenses.common.entities.FirebaseUser
import com.example.xpenses.common.model.FakeFirebaseAuth
import javax.inject.Inject

class FakeAuthentication @Inject constructor(private val auth: FakeFirebaseAuth) {
    fun getFirebaseUser(callback: (FirebaseUser?) -> Unit) {
        return callback(auth.currentUser)
    }

    fun resume() {}
    fun pause() {}

}