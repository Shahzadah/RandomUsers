package com.checkfer.randomusers.ui.userDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.checkfer.randomusers.data.extensions.formattedLocation
import com.checkfer.randomusers.data.extensions.formattedName
import com.checkfer.randomusers.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val USER_ARGS_KEY = "user"

@HiltViewModel
class UserDetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private var user: User = savedStateHandle.get<User>(USER_ARGS_KEY)!!

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val location = MutableLiveData<String>()
    val image = MutableLiveData<String>()
    val launchDialpad = MutableLiveData<String>()
    val launchEmail = MutableLiveData<String>()

    init {
        name.value = user.name.formattedName()
        email.value = user.email
        location.value = user.location.formattedLocation()
        image.value = user.picture.large
    }

    fun callButtonClicked() {
        launchDialpad.value = user.phone
    }

    fun emailButtonClicked() {
        launchEmail.value = user.email
    }
}