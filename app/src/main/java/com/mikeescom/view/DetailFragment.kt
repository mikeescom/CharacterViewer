package com.mikeescom.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikeescom.R

class DetailFragment : Fragment() {

    private val BASE_IMAGE_URL = "http://api.duckduckgo.com"
    private lateinit var title : TextView
    private lateinit var image : ImageView
    private lateinit var description : TextView
    private lateinit var titleStr : String
    private lateinit var imageStr : String
    private lateinit var descriptionStr : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val bundle = arguments
        titleStr = bundle!!.getString("TITLE").toString()
        imageStr = bundle!!.getString("IMAGE").toString()
        descriptionStr = bundle!!.getString("DESCRIPTION").toString()

        initViews(view)

        return view
    }

    private fun initViews(view: View) {
        title = view.findViewById(R.id.title)
        title.text = titleStr

        image = view.findViewById(R.id.image)
        Glide.with(requireContext())
            .load(BASE_IMAGE_URL + imageStr)
            .placeholder(R.drawable.default_placeholder)
            .into(image)

        description = view.findViewById(R.id.description)
        description.text = descriptionStr
    }
}