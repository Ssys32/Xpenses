package com.example.xpenses.common.model

import com.example.xpenses.common.utils.Constants
import com.example.xpenses.R
import com.example.xpenses.common.entities.WorkDay
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import javax.inject.Inject

open class FirestoreBase @Inject constructor(private val database: FirebaseFirestore) {

    private var workDayListener: ListenerRegistration? = null


    fun getDaysRef() = database.collection(Constants.COLL_DAYS)

    fun getDayRef(day: String) = getDaysRef().document(day)

    fun getWorkDayByDay(uid: String, day: String, callback: (WorkDay?) -> Unit) {
        workDayListener = getDayRef(day)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    callback(null)
                } else if (value != null && value.exists()) {
                    val workDay = value.toObject(WorkDay::class.java)
                    callback(workDay)
                } else {
                    callback(null)
                }
            }
    }

    fun pause() {
        workDayListener?.remove()
    }

    fun saveWorkDay(
        uid: String,
        day: String,
        startCapital: Double?,
        finalCapital: Double?,
        expenses: Double?,
        workDay: WorkDay,
        callback: (Int) -> Unit
    ) {
        startCapital?.let { workDay.startCapital = it }
        finalCapital?.let { workDay.finalCapital = it }
        expenses?.let { workDay.expenses = it }
        getDayRef(day)
            .set(workDay)
            .addOnSuccessListener {
                callback(R.string.today_save_success)
            }
            .addOnFailureListener {
                callback(R.string.today_save_failure)
            }
    }
}