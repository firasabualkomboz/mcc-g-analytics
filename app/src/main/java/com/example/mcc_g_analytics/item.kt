package com.example.mcc_g_analytics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics

class item : AppCompatActivity() {

//    firebase
private var mFirebaseAnalytics: FirebaseAnalytics? = null
    var db : SQL ?= null
    var firstTime:Long ?= null
    var items  = ArrayList<String>()

    lateinit var item1: TextView
    lateinit var item2: TextView
    lateinit var item3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_item)

        when(MainActivity.category){
            1 -> {
                items.add("freash food")
                items.add("freash")
                items.add("food")
            }
            2 -> {
                items.add(" shirt")
                items.add(" shirt black")
                items.add(" shirt white")
            }
            3 -> {
                items.add("iphone 7 ")
                items.add("iphone x ")
                items.add("htc phone ")
            }
        } // end category

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        db = SQL(this)

        item1 = findViewById(R.id.item_1)
        item1.text = items[0]
        item1.setOnClickListener {
            MainActivity.item = items[0]
            itemall("id_${items[0]}_item" , "${items[0]}_button" )
        }

        item2 = findViewById(R.id.item2)
        item2.text = items[0]
        item2.setOnClickListener {
            MainActivity.item = items[0]
            itemall("id_${items[0]}_item" , "${items[0]}_button" )
        }
        item3 = findViewById(R.id.item3)
        item3.text = items[0]
        item3.setOnClickListener {
            MainActivity.item = items[0]
            itemall("id_${items[0]}_item" , "${items[0]}_button" )
        }



    }


//    define function
private fun itemall( itemId : String , itemName : String ){
    val time = ((System.currentTimeMillis())/1000) - firstTime!!
    addToDB(screenTrack(),time.toString())
    clickEvent(itemId,itemName)
    Toast.makeText(this, "Success $time ", Toast.LENGTH_LONG).show()
    val intent = Intent(this , singelitem::class.java)
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
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "products_screen")
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "ProductsActivity")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        return "products_screen"
    }

    private fun addToDB(pageName : String , time :String){
        if (db!!.insertPerson(pageName , time)) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        firstTime = (System.currentTimeMillis())/1000
    }


}