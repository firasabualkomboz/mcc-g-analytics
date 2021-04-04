package com.example.mcc_g_analytics

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics

class singelitem : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        firstTime = (System.currentTimeMillis())/1000
    }

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    var db : SQL ?= null
    var firstTime:Long ?= null

    lateinit var name_item : TextView
    lateinit var desc_item : TextView

    @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.singel_item)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        db = SQL(this)

        name_item = findViewById(R.id.name_item)
        desc_item = findViewById(R.id.desc_item)

        when(MainActivity.item){
            "freash food" -> {
                name_item.text = "this is firest item "
                desc_item.text = "\"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without"
            }
            "freash" -> {
                name_item.text = "this is freash "
                desc_item.text = "\"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without"
            }
            "food" -> {
                name_item.text = "this is food "
                desc_item.text = "\"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without"
            }

//            category #2
            "shirt" -> {
                name_item.text = "this is shirt "
                desc_item.text = "\"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without"
            }
            "shirt black" -> {
                name_item.text = "this is shirt black "
                desc_item.text = "\"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without"
            }
            "shirt white" -> {
                name_item.text = "this is shirt white "
                desc_item.text = "\"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without"
            }


//            category #3
            "iphone 7 " -> {
                name_item.text = "this is iphone 7  "
                desc_item.text = "\"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without"
            }
            "iphone x" -> {
                name_item.text = "this is iphone x "
                desc_item.text = "\"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without"
            }
            "htc phone" -> {
                name_item.text = "this is htc phone "
                desc_item.text = "\"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without"
            }

        }

    }
    private fun screenTrack():String{
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "details_screen")
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "singelitem")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        return "details_screen"
    }
    private fun addToDB(pageName : String , time :String){
        if (db!!.insertPerson(pageName , time)) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        val time = ((System.currentTimeMillis())/1000) - firstTime!!
        addToDB(screenTrack() , time.toString())
    }

}