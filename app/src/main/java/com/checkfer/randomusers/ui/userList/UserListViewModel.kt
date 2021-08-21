package com.checkfer.randomusers.ui.userList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.checkfer.randomusers.data.model.User
import com.checkfer.randomusers.data.repository.RandomUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val repository: RandomUserRepository) :
    ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val randomUserList = MutableLiveData<List<User>>()
    val errorMessage = MutableLiveData<String>()

    init {
        callFetchRandomUserApi()
    }

    private fun callFetchRandomUserApi() {
        loading.value = true
        viewModelScope.launch {
            repository.fetchRandomUsers(onSuccess = {
                randomUserList.value = it
                loading.value = false
            }, onError = {
                errorMessage.value = it
                loading.value = false
            })
        }
    }
}