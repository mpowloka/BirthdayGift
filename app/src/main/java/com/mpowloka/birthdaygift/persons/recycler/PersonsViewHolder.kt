package com.mpowloka.birthdaygift.persons.recycler

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.birthdaygift.R
import com.mpowloka.birthdaygift.common.glide.GlideApp
import com.mpowloka.data.local.model.PersonWithPoints
import kotlinx.android.synthetic.main.view_holder_person.view.*

class PersonsViewHolder(
        private val activity: AppCompatActivity,
        itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(personWithPoints: PersonWithPoints) {
        itemView.name_tv.text = itemView.resources.getString(
                R.string.person_name_and_surname_placeholder,
                personWithPoints.firstName,
                personWithPoints.lastName
        )

        itemView.points_tv.text = personWithPoints.points.toString()

        GlideApp.with(activity)
                .load(personWithPoints.pictureUrl)
                .into(itemView.picture_iv)
    }

}