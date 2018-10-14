package com.mpowloka.birthdaygift.persons

import androidx.recyclerview.widget.LinearLayoutManager
import com.mpowloka.birthdaygift.persons.recycler.PersonsRecyclerAdapter
import dagger.Module
import dagger.Provides

@Module
class PersonsModule {

    @Provides
    fun providePersonsRecyclerAdapter(
            personsActivity: PersonsActivity
    ): PersonsRecyclerAdapter {
        return PersonsRecyclerAdapter(personsActivity, personsActivity.viewModel)
    }

    @Provides
    fun provideLinearLayoutManager(activity: PersonsActivity) = LinearLayoutManager(activity)

}