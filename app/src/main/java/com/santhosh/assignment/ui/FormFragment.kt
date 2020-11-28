package com.santhosh.assignment.ui

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.santhosh.assignment.R
import com.santhosh.assignment.databinding.FragmentFormBinding
import com.santhosh.assignment.model.PersonalInfo
import com.santhosh.assignment.utils.hideKeyboard
import com.santhosh.assignment.viewmodel.AgeCalculatorViewModel
import java.util.*

class FormFragment : Fragment() {

    private lateinit var viewModel: AgeCalculatorViewModel
    lateinit var binding: FragmentFormBinding
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_form, container, false)
        binding = DataBindingUtil.bind(view)!!
        initViewModel()
        initListeners()
        activity?.title = "Fill your details"
        return view
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AgeCalculatorViewModel::class.java)
        viewModel.enableNext.observe(viewLifecycleOwner, {
            binding.btnNext.isEnabled = it
        })
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initListeners() {
        binding.apply {
            etDob.setOnClickListener {
                showDatePicker(it.context)
            }
            btnNext.setOnClickListener {
                it.hideKeyboard()
                findNavController().navigate(
                    FormFragmentDirections.stepAgeDetail(
                        PersonalInfo(
                            etFirstName.text.toString(),
                            etLastName.text.toString(),
                            calendar
                        )
                    )
                )
            }
        }
    }

    private fun showDatePicker(context: Context) {
        val datePickerListener =
            DatePickerDialog.OnDateSetListener { _, year, month, date ->
                calendar.set(year, month, date)
                binding.etDob.setText(
                    StringBuilder()
                        .append(date).append("-")
                        .append(month + 1).append("-")
                        .append(year)
                )
            }

        val datePickerDialog = DatePickerDialog(
            context, R.style.DatePickerTheme,
            datePickerListener, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

}