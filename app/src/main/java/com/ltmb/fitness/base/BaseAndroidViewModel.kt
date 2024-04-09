package com.ltmb.fitness.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.ltmb.fitness.internal.popup.PopupCallback
import com.ltmb.fitness.internal.popup.PopupUiModel
import com.ltmb.fitness.internal.util.Event
import com.ltmb.fitness.navigation.NavigationCommand

abstract class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {
    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    var useCustomLoading = false

    fun navigate(directions: NavDirections) {
        _navigation.value = Event(NavigationCommand.ToDirection(directions))
    }

    fun navigate(deepLink: String) {
        _navigation.value = Event(NavigationCommand.ToDeepLink(deepLink))
    }

    fun navigate(@StringRes deepLinkRes: Int) {
        navigate(getString(deepLinkRes))
    }

    fun navigate(model: PopupUiModel, callback: PopupCallback?) {
        _navigation.value = Event(NavigationCommand.Popup(model, callback))
    }

    fun navigateBack() {
        _navigation.value = Event(NavigationCommand.Back)
    }

    fun setLoading(value: Boolean) {
        _loading.value = value
    }

    protected fun getString(@StringRes resId: Int): String {
        return getApplication<Application>().getString(resId)
    }

    protected fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return getApplication<Application>().getString(resId, formatArgs)
    }
}