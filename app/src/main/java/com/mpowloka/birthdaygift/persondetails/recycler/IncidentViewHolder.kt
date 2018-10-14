package com.mpowloka.birthdaygift.persondetails.recycler

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.birthdaygift.common.glide.GlideApp
import com.mpowloka.birthdaygift.persondetails.PersonDetailsViewModel
import com.mpowloka.data.local.model.Incident
import kotlinx.android.synthetic.main.view_holder_incident.view.*

class IncidentViewHolder(
        private val activity: AppCompatActivity,
        private val viewModel: PersonDetailsViewModel,
        itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(incident: Incident) {

        bindData(incident)

        itemView.root_view.setOnClickListener {
            //TODO
        }

    }

    private fun bindData(incident: Incident) {
        itemView.name_tv.text = incident.name

        itemView.points_tv.text = incident.points.toString()

        GlideApp.with(activity)
                .load(incident.pictureUrl)
                .into(itemView.picture_iv)
    }

}