package com.mpowloka.birthdaygift.persons

import android.os.Bundle
import com.mpowloka.architecture.base.BaseViewModelActivity
import com.mpowloka.birthdaygift.R

class PersonsActivity : BaseViewModelActivity<PersonsViewModel>() {

    override fun getViewModelClass() = PersonsViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persons)
    }
}
