package com.mpowloka.birthdaygift.persons

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpowloka.architecture.base.BaseViewModelActivity
import com.mpowloka.birthdaygift.R
import com.mpowloka.birthdaygift.persons.recycler.HideFabOnScrollListener
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

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
    }

    private fun setupRecycler() {
        recycler.adapter = personsRecyclerAdapter
        recycler.layoutManager = layoutManager
        recycler.addOnScrollListener(HideFabOnScrollListener(fab))

        viewModel.personsWithPoints.observe(this, Observer { personsWithPoints ->
            personsRecyclerAdapter.setDataSource(personsWithPoints)
        })
    }

}