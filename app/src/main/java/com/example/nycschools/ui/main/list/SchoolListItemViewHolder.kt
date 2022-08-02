package com.example.nycschools.ui.main.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.R
import com.example.nycschools.databinding.LayoutSchoolListItemBinding
import com.example.nycschools.ui.main.list.model.IOnSchoolListItemClickedListener
import com.example.nycschools.ui.main.list.model.SchoolUiModel

class SchoolListItemViewHolder(
    parent: ViewGroup,
    private val schoolListItemClickedListener: IOnSchoolListItemClickedListener,
    private val binding: LayoutSchoolListItemBinding = LayoutSchoolListItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    private var schoolId: String? = null
    private var schoolPhoneNumber: String? = null

    init {
        itemView.setOnClickListener {
            schoolId?.let {
                schoolListItemClickedListener.onSchoolSelected(it)
            }
        }

        binding.schoolPhone.setOnClickListener {
            schoolPhoneNumber?.let {
                schoolListItemClickedListener.onCallSchoolClicked(it)
            }
        }
    }

    fun bind(item: SchoolUiModel?) {
        item?.let { model ->
            schoolId = model.id
            binding.schoolName.text = model.name
            binding.schoolDescription.text = model.description
            if (model.phoneNumber.isEmpty()) {
                schoolPhoneNumber = null
                binding.schoolPhone.text = ""
                binding.schoolPhone.visibility = View.INVISIBLE
            } else {
                schoolPhoneNumber = model.phoneNumber
                binding.schoolPhone.text = binding.root.context.getString(R.string.call)
                binding.schoolPhone.visibility = View.VISIBLE
            }
        }
    }
}