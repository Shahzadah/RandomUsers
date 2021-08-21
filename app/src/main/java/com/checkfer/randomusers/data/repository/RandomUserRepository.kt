package com.checkfer.randomusers.data.repository

import com.checkfer.randomusers.data.api.ApiEndpoint
import com.checkfer.randomusers.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RandomUserRepository constructor(private val apiEndpoint: ApiEndpoint) {

    suspend fun fetchRandomUsers(onSuccess: (List<User>) -> Unit, onError: (String) -> Unit) {
        try {
            val response = withContext(Dispatchers.IO) { apiEndpoint.fetchRandomUsers() }
            response.body()?.takeIf { response.isSuccessful }?.let { onSuccess.invoke(it.results) }
                ?: onError.invoke(response.message())
        } catch (e: Exception) {
            onError.invoke(e.localizedMessage)
        }
    }
}