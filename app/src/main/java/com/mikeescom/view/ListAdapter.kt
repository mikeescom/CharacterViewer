package com.mikeescom.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikeescom.R
import com.mikeescom.model.dao.RelatedTopic

class ListAdapter(private var relatedTopicList: ArrayList<RelatedTopic>) : RecyclerView.Adapter<ListAdapter.ListAdapterHolder>() , Filterable{

    private lateinit var listener : OnClickItem
    private var relatedTopicFilteredList : ArrayList<RelatedTopic>

    init {
        relatedTopicFilteredList = relatedTopicList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ListAdapterHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListAdapterHolder, position: Int) {
        val relatedTopic: RelatedTopic = relatedTopicFilteredList[position]
        holder.characterName.text = relatedTopic.Text.split(" - ")[0]
        holder.itemView.setOnClickListener { listener.onClick(relatedTopic) }
    }

    override fun getItemCount(): Int {
        if (relatedTopicFilteredList.isNullOrEmpty()) {
            return 0
        }
        return relatedTopicFilteredList.size
    }

    fun setListener(listener : OnClickItem) {
        this.listener = listener
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    relatedTopicFilteredList = relatedTopicList
                } else {
                    val resultList = ArrayList<RelatedTopic>()
                    for (row in relatedTopicList) {
                        if (row.Text.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    relatedTopicFilteredList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = relatedTopicFilteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                relatedTopicFilteredList = results?.values as ArrayList<RelatedTopic>
                notifyDataSetChanged()
            }
        }
    }

    inner class ListAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterName: TextView = itemView.findViewById(R.id.character_name)
    }

    interface OnClickItem {
        fun onClick(relatedTopic: RelatedTopic)
    }
}