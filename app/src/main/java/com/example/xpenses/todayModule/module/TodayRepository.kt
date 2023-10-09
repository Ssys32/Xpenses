package com.example.xpenses.todayModule.module

import com.example.xpenses.common.entities.WorkDay
import com.example.xpenses.common.entities.FirebaseUser
import com.example.xpenses.common.model.FirestoreBase
import com.example.xpenses.common.utils.DomainUtils
import javax.inject.Inject

class TodayRepository @Inject constructor(
    private val database: FirestoreBase,
    //private val auth: FakeFirebaseAuth,
    private val user: FirebaseUser?,
    private val utils: DomainUtils
) {

    fun getWorkDayByDay(callback: (WorkDay?) -> Unit) {
        //val user = auth.currentUser
        if (user != null) {
            database.getWorkDayByDay(user.uid, utils.getToday()) {
                callback(it)
            }
        } else
            callback(null)
    }

    fun pause() {
        database.pause()
    }

    fun saveWorkDay(
        startCapital: Double?,
        finalCapital: Double?,
        expenses: Double?,
        workDay: WorkDay,
        callback: (Int?) -> Unit
    ) {
        //val user = auth.currentUser
        if (user != null) {
            database.saveWorkDay(
                user.uid, utils.getToday(), startCapital, finalCapital, expenses,
                workDay
            ) {
                callback(it)
            }
        } else {
            callback(null)
        }
    }
}