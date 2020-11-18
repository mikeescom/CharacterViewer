package com.mikeescom.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikeescom.R
import com.mikeescom.model.dao.Response
import com.mikeescom.viewmodel.MainViewModel

class ListFragment : Fragment() {
    private lateinit var progressBar : ProgressBar
    private lateinit var searchView : SearchView
    private lateinit var recyclerView : RecyclerView
    private lateinit var viewModel : MainViewModel
    private lateinit var adapter : ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        progressBar = view.findViewById(R.id.progress_bar)
        searchView = view.findViewById(R.id.search_view)
        recyclerView = view.findViewById(R.id.recycler_view)
        adapter = ListAdapter(requireContext())

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getCharactersResponseLiveData()?.observe(viewLifecycleOwner, {
                response -> response?.let { updateUI(it) }
        })

        viewModel.callCharactersApi()

        return view
    }

    private fun updateUI(response : Response) {
        progressBar.visibility = View.GONE
        adapter.setResults(response.RelatedTopics)
        recyclerView!!.addItemDecoration(
            DividerItemDecoration(
                recyclerView!!.context, DividerItemDecoration.VERTICAL
            )
        )
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}