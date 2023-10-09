package com.example.xpenses.recordModule.module

import com.example.xpenses.common.entities.WorkDay
import com.example.xpenses.common.model.FirestoreBase
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirestoreRecord @Inject constructor(database: FirebaseFirestore) : FirestoreBase(database) {
    fun getAllWorkDay(uid: String, callback: (List<WorkDay>?) -> Unit) {
        getDaysRef().get().addOnCompleteListener { tasks ->
            if (tasks.isSuccessful) {
                val workDays = mutableListOf<WorkDay>()
                tasks.result.documents.forEach { doc ->
                    doc.toObject(WorkDay::class.java)?.let { workDay ->
                        workDay.uid = doc.id
                        workDays.add(workDay)
                    }
                }
                callback(workDays)
            } else {
                callback(null)
            }
        }
    }
}