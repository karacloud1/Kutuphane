package com.cloud.kutuphane

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_signup.*

class LoginFragment : Fragment() {
    lateinit var sharedPreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        button_signup.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            Navigation.findNavController(it).navigate(action)
        }
        button_login.setOnClickListener {
            loginControl(view)
        }

    }
    fun loginControl(view : View){
        val user = User(editTextuserName.text.toString().replace(" ","") ,editTextPassword.text.toString().replace(" ",""))
        context?.let {
            val userId = DBHelper.getUserId(user.name,user.password, it)
            if (userId!=0){
                context?.let {
                    sharedPreferences = it.getSharedPreferences("com.cloud.kutuphane",
                        Context.MODE_PRIVATE)
                    sharedPreferences.edit().putInt("userId",userId).apply()
                }
                val action = LoginFragmentDirections.actionLoginFragmentToUserMainFragment(user.name)
                Navigation.findNavController(view).navigate(action)

            }else{
                Toast.makeText(it,"Kullanıcı adı veya şifre hatalı.",Toast.LENGTH_LONG).show()
            }
        }
    }

}