package com.bank.curreny.resourceProvider.extenstions

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bank.curreny.ui.R
import kotlinx.coroutines.flow.SharedFlow

fun <T> Fragment.collect(sharedFlow: SharedFlow<T>, block: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        sharedFlow.collect {
            block(it)
        }
    }
}

fun Fragment.showMessageDialog(message: String) {
    val builder = AlertDialog.Builder(this.requireActivity())
    builder.setMessage(message)
    builder.setPositiveButton("OK") { dialog, which ->
        dialog.dismiss()
    }
    builder.create().show()
}

