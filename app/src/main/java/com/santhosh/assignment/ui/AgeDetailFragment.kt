package com.santhosh.assignment.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.santhosh.assignment.R
import com.santhosh.assignment.databinding.FragmentAgeDetailBinding

class AgeDetailFragment : Fragment() {
    lateinit var binding: FragmentAgeDetailBinding
    private val args: AgeDetailFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_age_detail, container, false)
        binding = DataBindingUtil.bind(view)!!
        binding.info = args.info
        activity?.title = "Age detail"
        binding.btnGoBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return view
    }

}