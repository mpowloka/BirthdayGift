package com.mpowloka.birthdaygift.persondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpowloka.architecture.base.BaseViewModelActivity
import com.mpowloka.birthdaygift.R
import com.mpowloka.birthdaygift.common.glide.GlideApp
import com.mpowloka.birthdaygift.persondetails.recycler.PersonIncidentsRecyclerAdapter
import kotlinx.android.synthetic.main.activity_person_details.*
import javax.inject.Inject

class PersonDetailsActivity : BaseViewModelActivity<PersonDetailsViewModel>() {

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var personIncidentsRecyclerAdapter: PersonIncidentsRecyclerAdapter

    var displayedPersonLocalId = -1L

    override fun getViewModelClass() = PersonDetailsViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        readDataOrFinish()

        bindData()

        setupRecycler()
    }

    private fun bindData() {
        viewModel
                .getPersonWithPointsAndRank(displayedPersonLocalId)
                .observe(this, Observer { personWithPointsAndRank ->
                    if (personWithPointsAndRank == null) {
                        finish()
                    } else {
                        first_name_tv.text = personWithPointsAndRank.personWithPoints.firstName
                        last_name_tv.text = personWithPointsAndRank.personWithPoints.lastName
                        points_tv.text = personWithPointsAndRank.personWithPoints.points.toString()
                        ranking_tv.text = personWithPointsAndRank.rank.toString()

                        GlideApp
                                .with(this)
                                .load(personWithPointsAndRank.personWithPoints.pictureUrl)
                                .into(person_picture_iv)
                    }
                })
    }

    private fun readDataOrFinish() {
        displayedPersonLocalId = intent.getLongExtra(DISPLAYED_PERSON_LOCAL_ID_KEY, -1L)
        if (displayedPersonLocalId == -1L) {
            finish()
        }
    }

    private fun setupRecycler() {
        recycler.layoutManager = layoutManager
        recycler.adapter = personIncidentsRecyclerAdapter

        viewModel.getPersonIncidents(displayedPersonLocalId).observe(this, Observer { incidents ->
            personIncidentsRecyclerAdapter.setDataSource(incidents)
        })
    }

    companion object {

        private const val DISPLAYED_PERSON_LOCAL_ID_KEY = "DISPLAYED_PERSON_LOCAL_ID"

        fun launch(source: Context, localPersonId: Long) {
            val intent = Intent(source, PersonDetailsActivity::class.java).apply {
                putExtra(DISPLAYED_PERSON_LOCAL_ID_KEY, localPersonId)
            }
            source.startActivity(intent)
        }

    }

}