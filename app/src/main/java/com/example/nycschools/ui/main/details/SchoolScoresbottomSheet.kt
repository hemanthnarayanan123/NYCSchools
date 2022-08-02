package com.example.nycschools.ui.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.nycschools.R
import com.example.nycschools.databinding.FragmentSchoolDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SchoolDetailsBottomSheet : BottomSheetDialogFragment() {
    companion object {
        private const val SELECTED_SCHOOL_ID = "SELECTED_SCHOOL_ID"
        fun newInstance(schoolIdSelected: String): SchoolDetailsBottomSheet {
            val bundle = bundleOf(SELECTED_SCHOOL_ID to schoolIdSelected)
            return SchoolDetailsBottomSheet().apply { arguments = bundle }
        }
    }

    private lateinit var binding: FragmentSchoolDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSchoolDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[SchoolScoresViewModel::class.java]
        if (savedInstanceState == null) {
            requireArguments().getString(SELECTED_SCHOOL_ID)?.let {
                viewModel.fetchSchoolSATScores(
                    requireContext(),
                    it
                )
            } ?: run {
                Toast.makeText(requireContext(), R.string.generic_error, Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }
        viewModel.resultsErrorLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), R.string.generic_error, Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }

        viewModel.resultsLiveData.observe(viewLifecycleOwner) {
            it?.let { schoolsScoresDataList ->
                if (schoolsScoresDataList.school == null) {
                    Toast.makeText(requireContext(), R.string.generic_error, Toast.LENGTH_SHORT)
                        .show()
                    dismiss()
                }
                binding.schoolName.text = schoolsScoresDataList.school?.name
                binding.schoolTestTakersCount.text = schoolsScoresDataList.school?.totalAppeared
                binding.satMathScore.text = schoolsScoresDataList.school?.satMath
                binding.satReadingScore.text = schoolsScoresDataList.school?.satReading
                binding.satWritingScore.text = schoolsScoresDataList.school?.satWriting
            }
        }

        binding.schoolDetailsCloseButton.setOnClickListener {
            dismiss()
        }
    }
}