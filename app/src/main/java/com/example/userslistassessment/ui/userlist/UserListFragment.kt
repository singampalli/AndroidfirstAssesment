package com.example.userslistassessment.ui.userlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userslistassessment.R
import com.example.userslistassessment.api.ApiClient
import com.example.userslistassessment.model.User
import com.example.userslistassessment.ui.adapter.UserAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private var users = mutableListOf<User>()
    private var currentPage = 1
    private val pageSize = 7
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userAdapter = UserAdapter(users) { user ->
            val action = UserListFragmentDirections
                .actionUserListFragmentToUserDetailFragment(user.name, user.email, user.username)
            findNavController().navigate(action)
        }
        recyclerView.adapter = userAdapter

        // Add scroll listener for pagination
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    loadMoreUsers()
                }
            }
        })
        loadMoreUsers()

        return view
    }

    private fun loadMoreUsers() {
        isLoading = true
        ApiClient.apiService.getUsers(currentPage, pageSize).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    Log.d("API", "URL: ${call.request().url}")
                    val newUsers = response.body() ?: emptyList()
                    users.addAll(newUsers)
                    userAdapter.notifyDataSetChanged()
                    currentPage++
                } else {
                    Toast.makeText(requireContext(), "Failed to load users", Toast.LENGTH_SHORT).show()
                }
                isLoading = false
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(requireContext(), "Failed to load users", Toast.LENGTH_SHORT).show()
                isLoading = false
            }
        })
    }
}
