package com.mpowloka.birthdaygift.persons

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpowloka.architecture.base.BaseViewModelActivity
import com.mpowloka.birthdaygift.R
import com.mpowloka.birthdaygift.common.recycler.HideFabOnScrollListener
import com.mpowloka.birthdaygift.persondetails.PersonDetailsActivity
import com.mpowloka.birthdaygift.persons.recycler.PersonsRecyclerAdapter
import kotlinx.android.synthetic.main.activity_persons.*
import javax.inject.Inject

class PersonsActivity : BaseViewModelActivity<PersonsViewModel>() {

    @Inject
    lateinit var personsRecyclerAdapter: PersonsRecyclerAdapter

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    override fun getViewModelClass() = PersonsViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persons)

        setupToolbar()

        setupRecycler()

        observeCardsClicks()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeCardsClicks() {
        viewModel.personCardsClicks.observe(this, Observer { clickedPerson ->
            if (clickedPerson != null) {
                PersonDetailsActivity.launch(this, clickedPerson.personWithPoints.localId)
            }
        })
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
    }

    private fun setupRecycler() {
        recycler.adapter = personsRecyclerAdapter
        recycler.layoutManager = layoutManager
        recycler.addOnScrollListener(HideFabOnScrollListener(fab))

        viewModel.personsWithPointsAndRank.observe(this, Observer { personsWithPoints ->
            personsRecyclerAdapter.setDataSource(personsWithPoints)
        })
    }

}