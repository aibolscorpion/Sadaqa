package kz.almaty.sadaqa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.almaty.sadaqa.R
import kz.almaty.sadaqa.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel = MainActivityViewModel(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      createTableWithPerson()
        initRecyclerView()

    }

    fun createTableWithPerson(){
        viewModel.createTableWithPerson()
        viewModel.boolLiveData.observe(this, { boolean ->
            if(boolean){
                Toast.makeText(this, R.string.successfully_added, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, R.string.addition_failure, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun initRecyclerView(){
        val peopleInNeedRV : RecyclerView = findViewById(R.id.peopleInNeedRV)
        viewModel.peopleLiveData.observe(this, { people ->
            peopleInNeedRV.adapter = PeopleInNeedListAdapter(applicationContext, people)
        })
        peopleInNeedRV.layoutManager = LinearLayoutManager(this)

        viewModel.getPeopleFromFirebaseDB()
    }
}