package kz.almaty.sadaqa.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val liveData =  MutableLiveData<List<String>>()
    fun setLiveData(){
        liveData.value = fillData()
    }
    fun getLiveData() : LiveData<List<String>> {
        return liveData
    }
    fun fillData() : List<String>{
        val listOfString = mutableListOf<String>()
        (1..10).forEach { value ->
            listOfString.add("$value person")
        }
        return listOfString
    }
}