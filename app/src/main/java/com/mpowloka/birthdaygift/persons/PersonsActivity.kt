package com.mpowloka.birthdaygift.persons

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpowloka.architecture.base.BaseViewModelActivity
import com.mpowloka.birthdaygift.R
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

    private fun setupRecycler() {
        recycler.adapter = personsRecyclerAdapter
        recycler.layoutManager = layoutManager

        viewModel.personsWithPoints.observe(this, Observer { personsWithPoints ->
            personsRecyclerAdapter.setDataSource(personsWithPoints)
        })
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

}