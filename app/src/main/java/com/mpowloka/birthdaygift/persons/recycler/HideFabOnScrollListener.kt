package com.mpowloka.birthdaygift.persons.recycler

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HideFabOnScrollListener(private val fab: FloatingActionButton): RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if(dy > 0) {
            fab.hide()
        } else {
            fab.show()
        }
    }
}