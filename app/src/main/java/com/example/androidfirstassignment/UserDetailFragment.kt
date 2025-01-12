package com.example.androidfirstassignment.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.androidfirstassignment.R
import com.example.androidfirstassignment.model.User

class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = arguments?.getParcelable<User>("user")

        val userName: TextView = view.findViewById(R.id.userName)
        val userEmail: TextView = view.findViewById(R.id.userEmail)

        user?.let {
            userName.text = it.name
            userEmail.text = it.email
        }
    }
}
