package com.dedechandran.storiesapps.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Context?.hideSoftKeyboard(view: View?) {
    if (view == null) return
    val imm = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context?.showSoftKeyboard(view: View?) {
    val imm = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}