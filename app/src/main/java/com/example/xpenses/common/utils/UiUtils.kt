package com.example.xpenses.common.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UiUtils @Inject constructor(@ApplicationContext val context: Context) {

    fun hideKeyboard(view: View) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}