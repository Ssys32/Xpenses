package com.example.xpenses.recordModule.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xpenses.BR
import com.example.xpenses.R
import com.example.xpenses.common.entities.WorkDay
import com.example.xpenses.common.utils.DomainUtils
import com.example.xpenses.databinding.ItemRecordBinding
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class RecordAdapter @Inject constructor(
    @ActivityContext private val context: Context,
    private val utils: DomainUtils, diff: WorkDayDiff
) :
    ListAdapter<WorkDay, RecyclerView.ViewHolder>(diff) {

    private lateinit var listener: OnClickListener

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemRecordBinding>(view)
        fun setListener(workDay: WorkDay){
            binding?.root?.setOnClickListener { listener.onClick(workDay) }
        }
    }

    fun setOnClickListener(listener: OnClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_record, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val workDay = getItem(position)
        with(holder as ViewHolder) {
            setListener(workDay)
            binding?.setVariable(BR.workDay, workDay)
            binding?.setVariable(BR.utils, utils)
            binding?.executePendingBindings()
        }
    }
}
