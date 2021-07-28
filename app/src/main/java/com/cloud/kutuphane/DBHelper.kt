package com.cloud.kutuphane

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

object DBHelper {

    fun checkUniqueUserName(checkUserName : String, context : Context) : Boolean{
        var unique = false
        try {
            context.let {
                val db = it.openOrCreateDatabase("users", Context.MODE_PRIVATE,null)


                val cursor = db.rawQuery("SELECT * FROM users",null)
                val userNameColumnIndex = cursor.getColumnIndex("userName")
                while(cursor.moveToNext()){
                    if (cursor.getString(userNameColumnIndex).equals(checkUserName)){
                        unique = true
                    }
                }
                cursor.close()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return unique
    }

    fun getUserId(checkUserName : String, checkPassw : String, context: Context) : Int{

        var userId = 0
        try{
            context.let {
                val database = it.openOrCreateDatabase("users", AppCompatActivity.MODE_PRIVATE, null)

                val cursor = database.rawQuery("SELECT * FROM users where userName = ? and userPassword = ?", arrayOf(checkUserName,checkPassw))
                val idColumnIndex = cursor.getColumnIndex("id")


                if(cursor.moveToNext()){
                   userId = cursor.getInt(idColumnIndex)
                }
                cursor.close()
            }

        }catch (e : Exception){
            e.printStackTrace()

        }
        return userId
    }





}