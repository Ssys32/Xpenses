package com.example.xpenses.common.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext

@InstallIn(FragmentComponent::class)
@Module
object AdapterModule {

    @Provides
    fun provideLayoutManager(@ActivityContext context: Context): LayoutManager =
        LinearLayoutManager(context)
}