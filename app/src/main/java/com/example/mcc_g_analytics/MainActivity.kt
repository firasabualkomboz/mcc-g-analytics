package com.example.mcc_g_analytics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {

override fun onResume() {
super.onResume()
firstTime = (System.currentTimeMillis())/1000
}

private var mFirebaseAnalytics: FirebaseAnalytics? = null
var db : SQL ?= null
var firstTime:Long ?= null


lateinit var btn_food: Button
lateinit var btn_clothes: Button
lateinit var btn_electronic: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        on jloin project with firebase
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        db = SQL(this)

//        on click btn
//        start category # 1
        btn_food = findViewById(R.id.btn_food)
        btn_food.setOnClickListener {
            itemall("id_food_item" , "food_button" )
        }

        btn_clothes = findViewById(R.id.btn_clothes)
        btn_clothes.setOnClickListener {
            itemall( "id_clothes_item" , "clothes_button")
        }

        btn_electronic = findViewById(R.id.btn_electronic)
        btn_electronic.setOnClickListener {
            itemall( "id_electronic_item" , "electronic_button" )
        }


    }

    private fun itemall( itemId : String , itemName : String ){
        val time = ((System.currentTimeMillis())/1000) - firstTime!!
        addToDB(screenTrack(),time.toString())
        clickEvent(itemId,itemName)
        Toast.makeText(this, "Success $time ", Toast.LENGTH_LONG).show()
        val intent = Intent(this , com.example.mcc_g_analytics.item::class.java)
        startActivity(intent)
    }
    private fun clickEvent(id: String, name: String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Button")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
    private fun screenTrack():String{
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "items")
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        return "categories_screen"
    }
    private fun addToDB(pageName : String , time :String){
        if (db!!.insertPerson(pageName , time)) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        var category : Int ?= null
        var item : String ?= null

    }


}