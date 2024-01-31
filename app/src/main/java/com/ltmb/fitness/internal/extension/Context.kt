package com.ltmb.fitness.internal.extension

import android.content.Context
import android.widget.Toast
import com.ltmb.fitness.internal.popup.Popup
import com.ltmb.fitness.internal.popup.PopupCallback
import com.ltmb.fitness.internal.popup.PopupUiModel

fun Context?.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun Context.showPopup(uiModel: PopupUiModel, callback: PopupCallback? = null) {
    Popup(this, uiModel, callback).show()
}
