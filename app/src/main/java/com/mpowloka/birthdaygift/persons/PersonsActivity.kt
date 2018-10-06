package com.mpowloka.birthdaygift.persons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mpowloka.birthdaygift.R
import dagger.android.AndroidInjection

class PersonsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persons)

    }
}
