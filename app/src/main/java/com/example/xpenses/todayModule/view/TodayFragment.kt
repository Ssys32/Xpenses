package com.example.xpenses.todayModule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.xpenses.BR
import com.example.xpenses.common.utils.UiUtils
import com.example.xpenses.databinding.FragmentTodayBinding
import com.example.xpenses.todayModule.viewModule.TodayViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var utils: UiUtils

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setUpObservers()
        setupButtons()
    }

    private fun setUpObservers() {
        binding.viewModel?.let {
            it.isHideKeyboard.observe(viewLifecycleOwner) { isHide ->
                if (isHide) utils.hideKeyboard(binding.root)
            }
            it.snackbarMsg.observe(viewLifecycleOwner) { resMsg ->
                Snackbar.make(binding.root, resMsg, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupViewModel() {
        val vm: TodayViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    private fun setupButtons() {
        with(binding) {
            btnStartSave.setOnClickListener {
                saveWorkDay(
                    startCapital = etStartCapital.text.toString().toDouble()
                )
            }
            btnFinaltSave.setOnClickListener {
                saveWorkDay(
                    finalCapital = etFinalCapital.text.toString().toDouble()
                )
            }
            btnExpensesSave.setOnClickListener {
                saveWorkDay(
                    expenses = etExpensesCapital.text.toString().toDouble()
                )
            }
        }

    }

    private fun saveWorkDay(
        startCapital: Double? = null,
        finalCapital: Double? = null,
        expenses: Double? = null
    ) {
        binding.viewModel?.saveWorkDay(startCapital, finalCapital, expenses)
    }

    override fun onResume() {
        super.onResume()
        binding.viewModel?.getWorkDay()
    }

    override fun onPause() {
        super.onPause()
        binding.viewModel?.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}