package com.example.xpenses.common.model

import android.net.Uri
import com.example.xpenses.common.entities.FirebaseUser
import javax.inject.Inject

class FakeFirebaseAuth @Inject constructor() {
    val currentUser: FirebaseUser = FirebaseUser(
        "sys32",
        Uri.parse("https://1000marcas.net/wp-content/uploads/2020/01/Logo-Android.png"),
        "Sys32"
    )
}