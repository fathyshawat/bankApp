package com.bank.curreny.resourceProvider.extenstions

import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.constraintlayout.widget.Group
import com.bank.curreny.ui.R

fun View.show() {
    if (visibility == View.VISIBLE) return

    visibility = View.VISIBLE
    if (this is Group) {
        this.requestLayout()
    }
}

fun View.hide() {
    if (visibility == View.GONE) return

    visibility = View.GONE
    if (this is Group) {
        this.requestLayout()
    }
}

fun View.invisible() {
    if (visibility == View.INVISIBLE) return

    visibility = View.INVISIBLE
    if (this is Group) {
        this.requestLayout()
    }
}

fun Spinner.init(context: Context, list: List<String>) {
    val adapter = ArrayAdapter(context, R.layout.custom_spinner_item, list)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    this.adapter = adapter
}