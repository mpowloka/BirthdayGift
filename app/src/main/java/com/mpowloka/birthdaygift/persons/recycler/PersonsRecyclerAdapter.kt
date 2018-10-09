package com.mpowloka.birthdaygift.persons.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.birthdaygift.R
import com.mpowloka.data.local.model.PersonWithPoints

class PersonsRecyclerAdapter(
        private val activity: AppCompatActivity
) : RecyclerView.Adapter<PersonsViewHolder>() {

    private var dataSource = emptyList<PersonWithPoints>()

    fun setDataSource(dataSource: List<PersonWithPoints>?) {
        this.dataSource = dataSource?.sortedBy { -it.points } ?: emptyList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_person, parent, false)
        return PersonsViewHolder(activity, view)
    }

    override fun onBindViewHolder(holder: PersonsViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    override fun getItemCount() = dataSource.size
}