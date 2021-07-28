package com.cloud.kutuphane

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_signup.*
import java.lang.Exception
import java.lang.reflect.Executable


class SignupFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_signupDone.setOnClickListener {
            userRegister(it)
        }
    }

    fun userRegister(view : View){
        try {
            context?.let {
                val db = it.openOrCreateDatabase("users",Context.MODE_PRIVATE,null)

                val user = User(editTextuserName_signup.text.toString().replace(" ","") ,editTextuserPassw_signup.text.toString().replace(" ","") )

                 if (user.name == "" || user.name == null || user.password == "" || user.password == null){
                Toast.makeText(context,"Lütfen boş alan bırakmayın!",Toast.LENGTH_LONG).show()
                 }

                else if(DBHelper.checkUniqueUserName(user.name,it)){
                    Toast.makeText(context,"Lütfen başka bir kullanıcı adı giriniz.",Toast.LENGTH_SHORT).show()
                }

                else{
                     val sqlString = "INSERT INTO users (userName,userPassword) VALUES (?,?)"
                     val statement = db.compileStatement(sqlString)
                     statement.bindString(1,user.name)
                     statement.bindString(2,user.password)
                     statement.execute()
                     Toast.makeText(context,"Kayıt olundu.",Toast.LENGTH_SHORT).show()
                     val action = SignupFragmentDirections.actionSignupFragmentToLoginFragment()
                     Navigation.findNavController(view).navigate(action)
                }

            }
        }catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(context,"Hata: ${e}",Toast.LENGTH_LONG).show()
        }

    }
}