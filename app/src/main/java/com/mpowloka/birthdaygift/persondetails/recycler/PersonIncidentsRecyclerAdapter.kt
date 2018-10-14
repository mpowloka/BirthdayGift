package com.mpowloka.birthdaygift.persondetails.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.birthdaygift.R
import com.mpowloka.birthdaygift.persondetails.PersonDetailsViewModel
import com.mpowloka.data.local.model.Incident

class PersonIncidentsRecyclerAdapter(
        private val activity: AppCompatActivity,
        private val viewModel: PersonDetailsViewModel
) : RecyclerView.Adapter<IncidentViewHolder>() {

    private var dataSource = emptyList<Incident>()

    fun setDataSource(dataSource: List<Incident>?) {
        this.dataSource = dataSource ?: emptyList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_incident, parent, false)
        return IncidentViewHolder(activity, viewModel, view)
    }

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    override fun getItemCount() = dataSource.size
}