package com.mpowloka.birthdaygift.persons.recycler

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mpowloka.birthdaygift.R
import com.mpowloka.birthdaygift.common.glide.GlideApp
import com.mpowloka.birthdaygift.persons.PersonsViewModel
import com.mpowloka.data.local.model.PersonWithPointsAndRank
import kotlinx.android.synthetic.main.view_holder_person.view.*

class PersonViewHolder(
        private val activity: AppCompatActivity,
        private val viewModel: PersonsViewModel,
        itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(personWithPointsAndRank: PersonWithPointsAndRank) {

        bindData(personWithPointsAndRank)

        itemView.root_view.setOnClickListener {
            viewModel.personCardsClicks.notifyHappened(personWithPointsAndRank)
        }

    }

    private fun bindData(personWithPointsAndRank: PersonWithPointsAndRank) {
        itemView.name_tv.text = itemView.resources.getString(
                R.string.person_name_and_surname_placeholder,
                personWithPointsAndRank.personWithPoints.firstName,
                personWithPointsAndRank.personWithPoints.lastName
        )

        itemView.points_tv.text = personWithPointsAndRank.personWithPoints.points.toString()

        GlideApp.with(activity)
                .load(personWithPointsAndRank.personWithPoints.pictureUrl)
                .into(itemView.picture_iv)
    }

}