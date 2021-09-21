package kz.almaty.sadaqa.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kz.almaty.sadaqa.R
import kz.almaty.sadaqa.model.Person

class MainActivityViewModel(val context : Context) : ViewModel() {
    lateinit var dataBase : DatabaseReference
    val peopleLiveData =  MutableLiveData<List<Person>>()
    val boolLiveData = MutableLiveData<Boolean>()

    fun getPeopleFromFirebaseDB() {
        val tablename = context.getString(R.string.firebase_table_name)
        val peopleList = ArrayList<Person>()

        dataBase = Firebase.database.getReference(tablename)
        dataBase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(personSnapshot in snapshot.children){
                        val person = personSnapshot.getValue(Person::class.java)
                        peopleList.add(person!!)
                    }
                    peopleLiveData.value = peopleList
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun createTableWithPerson() {
        val tablename = context.resources.getString(R.string.firebase_table_name)
        val personName = context.getString(R.string.person_name)
        val personDescription = context.getString(R.string.person_description)

        dataBase = Firebase.database.getReference(tablename)

        val person = Person(personName, personDescription)
        dataBase.child(personName).setValue(person).addOnSuccessListener {
            boolLiveData.value = true
        }.addOnFailureListener {
            boolLiveData.value = false
        }
    }
}