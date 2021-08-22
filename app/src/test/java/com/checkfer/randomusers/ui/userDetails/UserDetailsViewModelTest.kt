package com.checkfer.randomusers.ui.userDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.checkfer.randomusers.data.model.Location
import com.checkfer.randomusers.data.model.Name
import com.checkfer.randomusers.data.model.ProfilePic
import com.checkfer.randomusers.data.model.User
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(JUnit4::class)
class UserDetailsViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    lateinit var savedStateHandle: SavedStateHandle

    lateinit var viewModel: UserDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        every { savedStateHandle.get<User>("user") } returns getMockedUser()
    }

    @Test
    fun `verify name observer changed when viewModel gets created`() {
        //Arrange
        val nameObserver = mockk<Observer<String>>()
        every { nameObserver.onChanged(any()) } answers {}

        //Act
        viewModel = UserDetailsViewModel(savedStateHandle)
        viewModel.name.observeForever(nameObserver)

        //Assert
        verify { nameObserver.onChanged("Mr Chris Milburn") }
    }

    @Test
    fun `verify email observer changed when viewModel gets created`() {
        //Arrange
        val emailObserver = mockk<Observer<String>>()
        every { emailObserver.onChanged(any()) } answers {}

        //Act
        viewModel = UserDetailsViewModel(savedStateHandle)
        viewModel.email.observeForever(emailObserver)

        //Assert
        verify { emailObserver.onChanged("chris@gmail.com") }
    }

    @Test
    fun `verify location observer changed when viewModel gets created`() {
        //Arrange
        val locationObserver = mockk<Observer<String>>()
        every { locationObserver.onChanged(any()) } answers {}

        //Act
        viewModel = UserDetailsViewModel(savedStateHandle)
        viewModel.location.observeForever(locationObserver)

        //Assert
        verify { locationObserver.onChanged("London, United Kingdom") }
    }

    @Test
    fun `verify profile image observer changed when viewModel gets created`() {
        //Arrange
        val imageObserver = mockk<Observer<String>>()
        every { imageObserver.onChanged(any()) } answers {}

        //Act
        viewModel = UserDetailsViewModel(savedStateHandle)
        viewModel.image.observeForever(imageObserver)

        //Assert
        verify { imageObserver.onChanged("large_pic_url") }
    }

    @Test
    fun `verify launchDialpad observer changed when callButtonClicked`() {
        //Arrange
        val dialpadObserver = mockk<Observer<String>>()
        every { dialpadObserver.onChanged(any()) } answers {}
        viewModel = UserDetailsViewModel(savedStateHandle)
        viewModel.launchDialpad.observeForever(dialpadObserver)

        //Act
        viewModel.callButtonClicked()

        //Assert
        verify { dialpadObserver.onChanged("9876543210") }
    }

    @Test
    fun `verify launchEmail observer changed when emailButtonClicked`() {
        //Arrange
        val launchEmailObserver = mockk<Observer<String>>()
        every { launchEmailObserver.onChanged(any()) } answers {}
        viewModel = UserDetailsViewModel(savedStateHandle)
        viewModel.launchEmail.observeForever(launchEmailObserver)

        //Act
        viewModel.emailButtonClicked()

        //Assert
        verify { launchEmailObserver.onChanged("chris@gmail.com") }
    }

    private fun getMockedUser(): User {
        val user = mockk<User>()
        val name = mockk<Name>()
        every { name.title } returns "Mr"
        every { name.first } returns "Chris"
        every { name.last } returns "Milburn"
        every { user.name } returns name

        every { user.phone } returns "9876543210"
        every { user.email } returns "chris@gmail.com"

        val location = mockk<Location>()
        every { location.city } returns "London"
        every { location.country } returns "United Kingdom"
        every { user.location } returns location

        val picture = mockk<ProfilePic>()
        every { picture.medium } returns "medium_pic_url"
        every { picture.large } returns "large_pic_url"
        every { user.picture } returns picture
        return user
    }
}