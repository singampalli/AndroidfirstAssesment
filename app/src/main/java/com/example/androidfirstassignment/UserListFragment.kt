package com.example.androidfirstassignment.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfirstassignment.R
import com.example.androidfirstassignment.adapter.UserAdapter
import com.example.androidfirstassignment.model.User
import com.example.androidfirstassignment.network.RetrofitInstance
import kotlinx.coroutines.launch

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)

        fetchUsers()
    }

    private fun fetchUsers() {
        lifecycleScope.launch {
            try {
                val users = RetrofitInstance.api.getUsers()
                userAdapter = UserAdapter(users) { user ->
                    navigateToUserDetail(user)
                }
                recyclerView.adapter = userAdapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun navigateToUserDetail(user: User) {
        val bundle = Bundle().apply {
            putParcelable("user", user)
        }
        findNavController().navigate(R.id.action_userListFragment_to_userDetailFragment, bundle)
    }
}
