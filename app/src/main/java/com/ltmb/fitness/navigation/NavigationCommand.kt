package com.ltmb.fitness.navigation

import androidx.navigation.NavDirections
import com.ltmb.fitness.internal.popup.PopupCallback
import com.ltmb.fitness.internal.popup.PopupUiModel

sealed class NavigationCommand {
    data class ToDirection(val directions: NavDirections) : NavigationCommand()
    data class ToDeepLink(val deepLink: String) : NavigationCommand()
    data class Popup(val model: PopupUiModel, val callback: PopupCallback? = null) :
        NavigationCommand()

    data object Back : NavigationCommand()
}