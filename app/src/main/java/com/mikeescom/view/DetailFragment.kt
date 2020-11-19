package com.mikeescom.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikeescom.R

class DetailFragment : Fragment() {

    private val BASE_IMAGE_URL = "http://api.duckduckgo.com"
    private lateinit var title : TextView
    private lateinit var image : ImageView
    private lateinit var description : TextView
    private var titleStr : String = ""
    private var imageStr : String = ""
    private var descriptionStr : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val bundle = arguments

        initViews(view)
        if (arguments != null) {
            titleStr = bundle!!.getString("TITLE").toString()
            imageStr = bundle!!.getString("IMAGE").toString()
            descriptionStr = bundle!!.getString("DESCRIPTION").toString()
            updateUI()
        } else {
            setFragmentResultListener("REQUEST") { _, bundle ->
                titleStr = bundle.getString("TITLE").toString()
                imageStr = bundle.getString("IMAGE").toString()
                descriptionStr = bundle.getString("DESCRIPTION").toString()
                updateUI()
            }
        }

        return view
    }

    private fun initViews(view : View) {
        title = view.findViewById(R.id.title)
        image = view.findViewById(R.id.image)
        description = view.findViewById(R.id.description)
    }

    private fun updateUI() {
        title.text = titleStr
        Glide.with(requireContext())
            .load(BASE_IMAGE_URL + imageStr)
            .placeholder(R.drawable.default_placeholder)
            .into(image)
        description.text = descriptionStr
    }
}