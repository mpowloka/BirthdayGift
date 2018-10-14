package com.mpowloka.birthdaygift.persons.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.birthdaygift.R
import com.mpowloka.birthdaygift.persons.PersonsViewModel
import com.mpowloka.data.local.model.PersonWithPointsAndRank

class PersonsRecyclerAdapter(
        private val activity: AppCompatActivity,
        private val viewModel: PersonsViewModel
) : RecyclerView.Adapter<PersonViewHolder>() {

    private var dataSource = emptyList<PersonWithPointsAndRank>()

    fun setDataSource(dataSource: List<PersonWithPointsAndRank>?) {
        this.dataSource = dataSource?.sortedBy { it.rank } ?: emptyList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_person, parent, false)
        return PersonViewHolder(activity, viewModel, view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    override fun getItemCount() = dataSource.size
}