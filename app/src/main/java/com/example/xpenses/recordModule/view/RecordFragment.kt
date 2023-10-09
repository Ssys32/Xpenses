package com.example.xpenses.recordModule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.xpenses.BR
import com.example.xpenses.R
import com.example.xpenses.common.entities.WorkDay
import com.example.xpenses.recordModule.view.adapters.RecordAdapter
import com.example.xpenses.databinding.FragmentRecordBinding
import com.example.xpenses.recordModule.view.adapters.OnClickListener
import com.example.xpenses.recordModule.viewModel.RecordViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecordFragment : Fragment(), OnClickListener {

    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapter: RecordAdapter

    @Inject
    lateinit var lm: LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.workDays.observe(viewLifecycleOwner) { result ->
                adapter.submitList(result)
            }
            it.isError.observe(viewLifecycleOwner) { isError ->
                if (isError) {
                    Snackbar.make(binding.root, R.string.record_query_error, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun setupViewModel() {
        val vm: RecordViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    private fun setupRecyclerView() {
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = lm
            adapter = this@RecordFragment.adapter
        }.also { adapter.setOnClickListener(this) }
    }

    override fun onResume() {
        super.onResume()
        binding.viewModel?.getAllWorkDay()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerview.layoutManager = null
        _binding = null
    }

    override fun onClick(workDay: WorkDay) {
        Toast.makeText(requireActivity(), workDay.uid, Toast.LENGTH_SHORT).show()
    }
}