package com.example.nycschools.ui.main.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nycschools.ui.main.list.model.IOnSchoolListItemClickedListener
import com.example.nycschools.ui.main.list.model.SchoolUiModel

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SchoolUiModel>() {
    override fun areItemsTheSame(oldItem: SchoolUiModel, newItem: SchoolUiModel): Boolean {
        return oldItem.areItemsSame(newItem)
    }

    override fun areContentsTheSame(oldItem: SchoolUiModel, newItem: SchoolUiModel): Boolean {
        return oldItem.areContentsSame(newItem)
    }
}

class SchoolsListAdapter(private val schoolListItemClickedListener: IOnSchoolListItemClickedListener) : ListAdapter<SchoolUiModel, SchoolListItemViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolListItemViewHolder {
        return SchoolListItemViewHolder(parent, schoolListItemClickedListener)
    }

    override fun onBindViewHolder(holder: SchoolListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(schools: List<SchoolUiModel>) {
        submitList(schools)
    }
}