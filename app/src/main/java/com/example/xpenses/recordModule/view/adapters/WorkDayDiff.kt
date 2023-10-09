package com.example.xpenses.recordModule.view.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.xpenses.common.entities.WorkDay
import javax.inject.Inject

class WorkDayDiff @Inject constructor() : DiffUtil.ItemCallback<WorkDay>() {
    override fun areItemsTheSame(oldItem: WorkDay, newItem: WorkDay): Boolean =
        oldItem.uid == newItem.uid

    override fun areContentsTheSame(oldItem: WorkDay, newItem: WorkDay): Boolean =
        oldItem == newItem
}