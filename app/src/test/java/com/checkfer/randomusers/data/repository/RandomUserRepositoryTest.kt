package com.checkfer.randomusers.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.checkfer.randomusers.data.api.ApiEndpoint
import com.checkfer.randomusers.data.model.RandomUserResponse
import com.checkfer.randomusers.data.model.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Response

@RunWith(JUnit4::class)
class RandomUserRepositoryTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    lateinit var apiEndpoint: ApiEndpoint

    lateinit var repository: RandomUserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = RandomUserRepository(apiEndpoint)
    }

    @Test
    fun `verify listUser when fetchRandomUsers call is success`() = runBlocking {
        //Arrange
        val listUser: List<User> = listOf()
        val response = mockk<RandomUserResponse>()
        every { response.results } returns listUser
        coEvery { apiEndpoint.fetchRandomUsers() } returns Response.success(response)
        val successCallback = mockk<(List<User>) -> Unit>()
        val errorCallback = mockk<(String) -> Unit>()
        every { successCallback.invoke(any()) } answers {}
        every { errorCallback.invoke(any()) } answers {}

        //Act
        repository.fetchRandomUsers(successCallback, errorCallback)

        //Assert
        verify { successCallback.invoke(listUser) }
    }

    @Test
    fun `verify error message when fetchRandomUsers call is failed`() = runBlocking {
        //Arrange
        coEvery { apiEndpoint.fetchRandomUsers() } throws RuntimeException("Timeout error")
        val successCallback = mockk<(List<User>) -> Unit>()
        val errorCallback = mockk<(String) -> Unit>()
        every { successCallback.invoke(any()) } answers {}
        every { errorCallback.invoke(any()) } answers {}

        //Act
        repository.fetchRandomUsers(successCallback, errorCallback)

        //Assert
        verify { errorCallback.invoke("Timeout error") }
    }
}