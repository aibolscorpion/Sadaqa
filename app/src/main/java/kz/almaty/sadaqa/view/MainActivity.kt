package kz.almaty.sadaqa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.almaty.sadaqa.R
import kz.almaty.sadaqa.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var peopleInNeedRV : RecyclerView
    private val viewModel = MainActivityViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peopleInNeedRV = findViewById(R.id.peopleInNeedRV)
        viewModel.getLiveData().observe(this, Observer { values ->
            peopleInNeedRV.adapter = PeopleInNeedListAdapter(applicationContext, values)
        })
        viewModel.setLiveData()
        peopleInNeedRV.layoutManager = LinearLayoutManager(this)


    }


}