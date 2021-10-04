package kz.almaty.sadaqa.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.almaty.sadaqa.databinding.ItemRecyclerviewBinding
import kz.almaty.sadaqa.model.Person

class PeopleInNeedListAdapter(val context : Context, val peopleList : List<Person>) : RecyclerView.Adapter<PeopleInNeedListAdapter.PersonViewHolder>(){
    class PersonViewHolder(context : Context,val binding : ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener{
                val intent = Intent(context, PersonProfileActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(context, ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = peopleList[position]
        holder.binding.person = person
        Glide.with(context).load(person.photo).into(holder.binding.peopleInNeedPhoto)

    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

}