package com.checkfer.randomusers.ui.userList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.checkfer.randomusers.data.model.User
import com.checkfer.randomusers.data.repository.RandomUserRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import io.mockk.slot
import io.mockk.every
import io.mockk.invoke
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(JUnit4::class)
class UserListViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: RandomUserRepository

    lateinit var viewModel: UserListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = UserListViewModel(repository)
    }

    @Test
    fun `verify fetchRandomUserApi call and loading observer changed when viewModel gets created`() {
        //Arrange
        val loadingObserver = mockk<Observer<Boolean>>()
        every { loadingObserver.onChanged(any()) } answers {}
        viewModel.loading.observeForever(loadingObserver)

        //Assert
        coVerify { repository.fetchRandomUsers(any(), any()) }
        verify { loadingObserver.onChanged(true) }
    }

    @Test
    fun `verify randomUserList observer changed on successful fetchRandomUserApi call`() {
        //Arrange
        val randomUserListObserver = mockk<Observer<List<User>>>()
        every { randomUserListObserver.onChanged(any()) } answers {}
        viewModel.randomUserList.observeForever(randomUserListObserver)
        val listUser: List<User> = listOf()
        val slotSuccess = slot<(List<User>) -> Unit>()
        val slotError = slot<(String) -> Unit>()
        coVerify { repository.fetchRandomUsers(capture(slotSuccess), capture(slotError)) }

        //Act
        slotSuccess.invoke(listUser)

        //Assert
        verify { randomUserListObserver.onChanged(listUser) }
    }

    @Test
    fun `verify error observer changed on failure fetchRandomUserApi call`() {
        //Arrange
        val errorMessageObserver = mockk<Observer<String>>()
        every { errorMessageObserver.onChanged(any()) } answers {}
        viewModel.errorMessage.observeForever(errorMessageObserver)
        val error = "error thrown"
        val slotSuccess = slot<(List<User>) -> Unit>()
        val slotError = slot<(String) -> Unit>()
        coVerify { repository.fetchRandomUsers(capture(slotSuccess), capture(slotError)) }

        //Act
        slotError.invoke(error)

        //Assert
        verify { errorMessageObserver.onChanged(error) }
    }
}