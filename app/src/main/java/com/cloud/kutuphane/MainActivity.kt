package com.cloud.kutuphane

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try{

            val database = this.openOrCreateDatabase("books", MODE_PRIVATE, null)
            database.execSQL("CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY , artistName VARCHAR, releaseDate VARCHAR, bookName VARCHAR, bookImageUrl VARCHAR)")

//            database.execSQL("INSERT INTO books(artistName,releaseDate,bookName,bookImageUrl) VALUES ('C.M. Sutter','2021-04-10','Blood in the Bayou','https://is4-ssl.mzstatic.com/image/thumb/Publication114/v4/f0/29/f9/f029f93e-98f3-1a58-71eb-dc775a292932/0000696208.jpg/200x200bb.png')")
//            database.execSQL("INSERT INTO books(artistName,releaseDate,bookName,bookImageUrl) VALUES ('Jean Oram','2016-09-06','Tequila and Candy Drops','https://is4-ssl.mzstatic.com/image/thumb/Publication128/v4/79/71/44/79714429-4169-3cb8-b189-b76c5015093f/TequilaCandy-Drops_Jean_Oram_25meg.jpg/200x200bb.png');")
//            database.execSQL("INSERT INTO books(artistName,releaseDate,bookName,bookImageUrl) VALUES ('Apple Inc.','2020-09-16','iPhone User Guide','https://is4-ssl.mzstatic.com/image/thumb/Publication125/v4/0f/a7/f9/0fa7f9fd-b455-4a63-8b99-b966f91ec314/ePubCover_iPhone.png/200x200bb.png');")
//            database.execSQL("INSERT INTO books(artistName,releaseDate,bookName,bookImageUrl) VALUES ('Lexy Timms','2021-06-10','Neverending Dream Box Set  Books #1-3','https://is2-ssl.mzstatic.com/image/thumb/Publication125/v4/36/3a/1a/363a1acb-1012-d8a9-e04b-16a65e77c80f/0000728362.jpg/200x200bb.png');")
//            database.execSQL("INSERT INTO books(artistName,releaseDate,bookName,bookImageUrl) VALUES ('Aleatha Romig','2017-05-16','Plus One','https://is5-ssl.mzstatic.com/image/thumb/Publication123/v4/47/99/cf/4799cf7c-3255-91a9-50e8-788df01a346c/ARPlusOneBookCover525x8_MEDIUM-1.jpg/200x200bb.png');")
//            database.execSQL("INSERT INTO books(artistName,releaseDate,bookName,bookImageUrl) VALUES ('Kathryn Shay','2013-01-14','Michael''s Family','https://is2-ssl.mzstatic.com/image/thumb/Publication125/v4/34/bb/3a/34bb3a7b-8f58-1b21-a18d-08c3580504fe/Michaels-Family2500.jpg/200x200bb.png');")
//            database.execSQL("INSERT INTO books(artistName,releaseDate,bookName,bookImageUrl) VALUES ('Apple Inc.','2020-09-16','Apple Watch User Guide','https://is4-ssl.mzstatic.com/image/thumb/Publication125/v4/e0/4f/84/e04f8470-1a4e-3e82-f47a-2a0e3d265b32/ePubCover_Watch.png/200x200bb.png');")
//            database.execSQL("INSERT INTO books(artistName,releaseDate,bookName,bookImageUrl) VALUES ('Lexy Timms','2021-02-20','Walk Away','https://is3-ssl.mzstatic.com/image/thumb/Publication114/v4/a7/db/b7/a7dbb751-d3fe-dc1b-7cd0-8327d04b6a32/0000673210.jpg/200x200bb.png');")
//            database.execSQL("INSERT INTO books(artistName,releaseDate,bookName,bookImageUrl) VALUES ('Jane Henry','2021-04-23','Cormac','https://is5-ssl.mzstatic.com/image/thumb/Publication125/v4/27/4b/01/274b0115-5e13-440a-bf43-b5d20aedccc4/Cormac-A-Dark-Irish-Mafia-Romance-Apple.jpg/200x200bb.png');")
//            database.execSQL("INSERT INTO books(artistName,releaseDate,bookName,bookImageUrl) VALUES ('Riley Edwards','2020-02-25','Alec''s Dream','https://is2-ssl.mzstatic.com/image/thumb/Publication123/v4/96/28/de/9628defb-491c-b525-5468-6cadf39f8d47/GEMINI4_ALEC.jpg/200x200bb.png');")
//            database.execSQL("DELETE FROM books where id > 10")
            val cursor = database.rawQuery("SELECT * FROM books",null)
            val idColumnIndex = cursor.getColumnIndex("id")
            val artistNameColumnIndex = cursor.getColumnIndex("artistName")
            val bookNameColumnIndex = cursor.getColumnIndex("bookName")
            val bookImageUrlColumnIndex = cursor.getColumnIndex("bookImageUrl")
            val releaseDateColumnIndex = cursor.getColumnIndex("releaseDate")
            while(cursor.moveToNext()){
                println("id : ${cursor.getInt(idColumnIndex)}")
                println("yazar : ${cursor.getString(artistNameColumnIndex)}")
                println("kitap adı : ${cursor.getString(bookNameColumnIndex)}")
                println("kitap resim yolu : ${cursor.getString(bookImageUrlColumnIndex)}")
                println("kitap çıkış tarihi : ${cursor.getString(releaseDateColumnIndex)}")
            }
            cursor.close()

        }catch (e : Exception){
            e.printStackTrace()

        }

        try{

            val database2 = this.openOrCreateDatabase("users", MODE_PRIVATE, null)
            database2.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY , userName VARCHAR, userPassword VARCHAR)")

//            database2.execSQL("INSERT INTO users (userName,userPassword) VALUES ('hayati','bahar45')")
//            database2.execSQL("DELETE FROM users where id > 4")
            val cursor = database2.rawQuery("SELECT * FROM users",null)
            val idColumnIndex = cursor.getColumnIndex("id")
            val userNameColumnIndex = cursor.getColumnIndex("userName")
            val userPasswordColumnIndex = cursor.getColumnIndex("userPassword")

            while(cursor.moveToNext()){
                println("id : ${cursor.getInt(idColumnIndex)}")
                println("userName : ${cursor.getString(userNameColumnIndex)}")
                println("userPassword : ${cursor.getString(userPasswordColumnIndex)}")
            }
            cursor.close()

        }catch (e : Exception){
            e.printStackTrace()

        }
        try{

            val database3 = this.openOrCreateDatabase("borrowed", MODE_PRIVATE, null)
            database3.execSQL("CREATE TABLE IF NOT EXISTS borrowed (id INTEGER PRIMARY KEY , userId INT, bookId INT)")

//            database3.execSQL("INSERT INTO borrowed (userId,bookId) VALUES (1,1)")
            val cursor = database3.rawQuery("SELECT * FROM borrowed",null)
            val idColumnIndex = cursor.getColumnIndex("id")
            val userIdColumnIndex = cursor.getColumnIndex("userId")
            val bookIdColumnIndex = cursor.getColumnIndex("bookId")

            while(cursor.moveToNext()){
                println("id : ${cursor.getInt(idColumnIndex)}")
                println("userId : ${cursor.getString(userIdColumnIndex)}")
                println("bookId : ${cursor.getString(bookIdColumnIndex)}")
            }
            cursor.close()

        }catch (e : Exception){
            e.printStackTrace()

        }


    }
}