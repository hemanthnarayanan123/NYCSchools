package com.example.nycschools.ui.main.list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nycschools.R
import com.example.nycschools.databinding.FragmentMainBinding
import com.example.nycschools.ui.main.SchoolsListViewModel
import com.example.nycschools.ui.main.details.SchoolDetailsBottomSheet
import com.example.nycschools.ui.main.list.model.IOnSchoolListItemClickedListener


class SchoolsListFragment : Fragment(), IOnSchoolListItemClickedListener {

    companion object {
        fun newInstance() = SchoolsListFragment()
    }

    private lateinit var viewModel: SchoolsListViewModel
    private lateinit var binding: FragmentMainBinding
    private var adapter: SchoolsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SchoolsListViewModel::class.java]

        binding.swipeRefreshLayout.isRefreshing = true
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchSchoolsList(requireContext())
        }
        viewModel.resultsLiveData.observe(viewLifecycleOwner) {
            it?.let { list ->
                binding.swipeRefreshLayout.isRefreshing = false
                if (adapter == null) {
                    val layoutManager = LinearLayoutManager(requireContext())
                    binding.schoolsListView.layoutManager = layoutManager
                    adapter = SchoolsListAdapter(this)
                    binding.schoolsListView.adapter = adapter
                }
                binding.schoolsListView.addItemDecoration(
                    SchoolsListItemDividerDecoration(
                        requireContext()
                    )
                )
                adapter?.setData(list.schools)
            }
        }

        viewModel.resultsErrorLiveData.observe(viewLifecycleOwner) {
            it?.let {
                binding.swipeRefreshLayout.isRefreshing = false

                Toast.makeText(
                    requireContext(),
                    getString(R.string.generic_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        if (savedInstanceState == null) {
            viewModel.fetchSchoolsList(requireContext())
        }
    }

    override fun onSchoolSelected(id: String) {
        SchoolDetailsBottomSheet.newInstance(id).show(childFragmentManager, null)
    }

    override fun onCallSchoolClicked(number: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        requireContext().startActivity(intent)
    }
}