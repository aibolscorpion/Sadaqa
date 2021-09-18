package kz.almaty.sadaqa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var peopleInNeedRV : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peopleInNeedRV = findViewById(R.id.peopleInNeedRV)
        peopleInNeedRV.adapter = PeopleInNeedListAdapter(applicationContext, fillList())
        peopleInNeedRV.layoutManager = LinearLayoutManager(this)
    }
    fun fillList() : List<String>{
        val data = mutableListOf<String>()
        (0..10).forEach { i ->
            data.add("%i person")
        }
        return data
    }


}