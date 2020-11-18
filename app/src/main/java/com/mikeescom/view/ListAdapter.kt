package com.mikeescom.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikeescom.R
import com.mikeescom.model.dao.RelatedTopic

class ListAdapter(private val context: Context) : RecyclerView.Adapter<ListAdapter.ListAdapterHolder>() {

    private var results : Array<RelatedTopic>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ListAdapterHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListAdapterHolder, position: Int) {
        val relatedTopic: RelatedTopic = results!![position]
        holder.characterName.text = relatedTopic.Text
    }

    override fun getItemCount(): Int {
        if (results.isNullOrEmpty()) {
            return 0
        }
        return results!!.size
    }

    fun setResults(results: Array<RelatedTopic>?) {
        this.results = results
        notifyDataSetChanged()
    }

    inner class ListAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterName: TextView = itemView.findViewById(R.id.character_name)
    }
}