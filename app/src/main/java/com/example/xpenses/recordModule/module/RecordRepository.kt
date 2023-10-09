package com.example.xpenses.recordModule.module

import com.example.xpenses.common.entities.WorkDay
import com.example.xpenses.common.entities.FirebaseUser
import javax.inject.Inject

class RecordRepository @Inject constructor(
    private val database: FirestoreRecord,
    private val user: FirebaseUser?
) {

    fun getAllWorkDay(callback: (List<WorkDay>?) -> Unit) {
        if (user != null) {
            database.getAllWorkDay(user.uid){workDays ->
                callback(workDays)
            }
        }else {
            callback(null)
        }
    }

}