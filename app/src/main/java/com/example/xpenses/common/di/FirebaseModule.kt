package com.example.xpenses.common.di

import com.example.xpenses.common.entities.FirebaseUser
import com.example.xpenses.common.model.FakeFirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFireStore(): FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    fun provideFirebaseUser(auth: FakeFirebaseAuth): FirebaseUser? = auth.currentUser
}