package com.mpowloka.birthdaygift.persondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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

    private var displayedPersonLocalId = -1L

    override fun getViewModelClass() = PersonDetailsViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        readDataOrFinish()

        setupToolbar()

        setupRecycler()

        bindData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun bindData() {
        viewModel
                .getPersonWithPointsAndRank(displayedPersonLocalId)
                .observe(this, Observer { personWithPointsAndRank ->
                    if (personWithPointsAndRank == null) {
                        finish()
                    } else {
                        supportActionBar?.title = getString(
                                R.string.person_name_and_surname_placeholder,
                                personWithPointsAndRank.personWithPoints.firstName,
                                personWithPointsAndRank.personWithPoints.lastName
                        )
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

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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