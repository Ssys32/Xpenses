package com.example.xpenses.common.entities

import android.net.Uri

data class FirebaseUser(
    val uid: String = "",
    val photoUrl: Uri? = null,
    val displayName: String = ""
)
