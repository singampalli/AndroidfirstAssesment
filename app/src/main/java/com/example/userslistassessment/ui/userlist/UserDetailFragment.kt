package com.example.userslistassessment.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.userslistassessment.R

class UserDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_detail, container, false)

        // Retrieve arguments

        val args = UserDetailFragmentArgs.fromBundle(requireArguments())
        val name = args.userName
        val email = args.userEmail
        val id = args.userId

        val nameTextView = view.findViewById<TextView>(R.id.nameTextView)
        val emailTextView = view.findViewById<TextView>(R.id.emailTextView)
        val idTextView = view.findViewById<TextView>(R.id.idTextView)

        nameTextView.text = name
        emailTextView.text = email
        idTextView.text = id;

        return view
    }

}
