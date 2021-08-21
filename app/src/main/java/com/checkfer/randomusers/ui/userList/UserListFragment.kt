package com.checkfer.randomusers.ui.userList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.checkfer.randomusers.databinding.FragmentUserListBinding
import com.checkfer.randomusers.ui.userList.adapter.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner, {
            binding.progressBar.isVisible = it
        })

        viewModel.randomUserList.observe(viewLifecycleOwner, {
            val adapter = UserListAdapter(it) { user ->
                val action = UserListFragmentDirections.actionUserListToUserDetails(user)
                findNavController().navigate(action)
            }
            binding.recyclerView.adapter = adapter
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        })
    }
}