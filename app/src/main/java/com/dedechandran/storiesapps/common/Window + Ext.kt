package com.dedechandran.storiesapps.common

import android.view.Window
import android.view.WindowManager

fun Window.setUserInteractionState(interactionDisabled: Boolean) {
    if (interactionDisabled) {
        setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    } else {
        clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}