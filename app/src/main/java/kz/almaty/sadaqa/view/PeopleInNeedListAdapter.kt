package kz.almaty.sadaqa.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.almaty.sadaqa.R
import kz.almaty.sadaqa.model.Person

class PeopleInNeedListAdapter(val context : Context, val peopleList : List<Person>) : RecyclerView.Adapter<PeopleInNeedListAdapter.PersonViewHolder>(){
    class PersonViewHolder(context : Context, itemView : View): RecyclerView.ViewHolder(itemView){
        var nameTV : TextView ?= null
        var personIV : ImageView
        init {
            personIV = itemView.findViewById(R.id.peopleInNeedPhoto)
            nameTV = itemView.findViewById(R.id.nameTV)
            itemView.setOnClickListener{
                val intent = Intent(context, PersonProfileActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return PersonViewHolder(context, itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = peopleList[position]
        holder.nameTV?.text = person.name
        Glide.with(context).load(person.photo).into(holder.personIV)

    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

}