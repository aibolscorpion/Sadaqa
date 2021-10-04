package kz.almaty.sadaqa.viewModel

import android.content.Context
import android.graphics.Bitmap
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
    val imgLiveData = MutableLiveData<Bitmap>()

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
        val personPhoto = "https://media.istockphoto.com/photos/homeless-male-on-the-street-getting-help-from-female-picture-id1194548736?k=20&m=1194548736&s=612x612&w=0&h=QgRLxLq05D11VjDLnijTxodKUNZiEiUdufIREa3AJ0U="

        dataBase = Firebase.database.getReference(tablename)

        val person = Person(personName, personDescription, personPhoto)
        dataBase.child(personName).setValue(person).addOnSuccessListener {
            boolLiveData.value = true
        }.addOnFailureListener {
            boolLiveData.value = false
        }
    }
}