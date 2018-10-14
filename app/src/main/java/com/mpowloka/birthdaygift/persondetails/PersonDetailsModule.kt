package com.mpowloka.birthdaygift.persondetails

import androidx.recyclerview.widget.LinearLayoutManager
import com.mpowloka.birthdaygift.persondetails.recycler.PersonIncidentsRecyclerAdapter
import dagger.Module
import dagger.Provides

@Module
class PersonDetailsModule {

    @Provides
    fun provideLayoutManager(personDetailsActivity: PersonDetailsActivity) = LinearLayoutManager(personDetailsActivity)

    @Provides
    fun provideRecyclerAdapter(personDetailsActivity: PersonDetailsActivity) = PersonIncidentsRecyclerAdapter(personDetailsActivity, personDetailsActivity.viewModel)

}