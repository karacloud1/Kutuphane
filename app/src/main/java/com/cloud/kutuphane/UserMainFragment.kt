package com.cloud.kutuphane

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_user_main.*


class UserMainFragment : Fragment() {
    var userName = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        button_borrowBook.setOnClickListener {
            val action = UserMainFragmentDirections.actionUserMainFragmentToBookListFragment()
            Navigation.findNavController(view).navigate(action)
        }
        button_exit.setOnClickListener {
            val action = UserMainFragmentDirections.actionUserMainFragmentToLoginFragment()
            Navigation.findNavController(view).navigate(action)
        }
        button_returnBook.setOnClickListener {
            val action = UserMainFragmentDirections.actionUserMainFragmentToReturnBookFragment()
            Navigation.findNavController(view).navigate(action)
        }

        arguments?.let {
            userName = UserMainFragmentArgs.fromBundle(it).userName
            textView_userName.setText("Hoşgeldin, ${userName}")

        }
        super.onViewCreated(view, savedInstanceState)
    }


}