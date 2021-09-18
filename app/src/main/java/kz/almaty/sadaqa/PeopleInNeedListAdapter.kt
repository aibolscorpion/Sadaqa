package kz.almaty.sadaqa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PeopleInNeedListAdapter(val names : List<String>) : RecyclerView.Adapter<PeopleInNeedListAdapter.PersonViewHolder>(){
    class PersonViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var nameTV : TextView ?= null
        var personIV : ImageView ?= null
        init {
            personIV = itemView.findViewById(R.id.peopleInNeedPhoto)
            nameTV = itemView.findViewById(R.id.nameTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return PersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.nameTV?.text = names[position]

    }

    override fun getItemCount(): Int {
        return names.size
    }

}