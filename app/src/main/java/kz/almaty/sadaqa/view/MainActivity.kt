package kz.almaty.sadaqa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kz.almaty.sadaqa.R
import kz.almaty.sadaqa.databinding.ActivityMainBinding
import kz.almaty.sadaqa.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private val viewModel = MainActivityViewModel(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        createTableWithPerson()
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

        viewModel.peopleLiveData.observe(this, { people ->
            binding.peopleInNeedRV.adapter = PeopleInNeedListAdapter(applicationContext, people)
        })
        binding.peopleInNeedRV.layoutManager = LinearLayoutManager(this)

        viewModel.getPeopleFromFirebaseDB()
    }
}