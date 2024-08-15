package com.trios2024evdj.listmaker.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.trios2024evdj.listmaker.R
import com.trios2024evdj.listmaker.databinding.FragmentMainBinding
import com.trios2024evdj.listmaker.models.TaskList

class MainFragment(val clickListener: MainFragmentInteractionListener) : Fragment(),
ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    interface MainFragmentInteractionListener {
        fun listItemTapped(list: TaskList)
    }

    private lateinit var binding: FragmentMainBinding

    companion object {
        fun newInstance(clickListener: MainFragmentInteractionListener) =
            MainFragment(clickListener)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.listsRecyclerview.layoutManager =
            LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(requireActivity(),
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity())))
            .get(MainViewModel::class.java)
        val recyclerViewAdapter =
            ListSelectionRecyclerViewAdapter(viewModel.lists, this)

        binding.listsRecyclerview.adapter = recyclerViewAdapter

        viewModel.onListAdded = {
            recyclerViewAdapter.listsUpdated()
        }

        return binding.root
    }

    override fun listItemClicked(list: TaskList) {
        clickListener.listItemTapped(list)
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(requireActivity(),
//            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity())))
//            .get(MainViewModel::class.java)
//        val recyclerViewAdapter =
//            ListSelectionRecyclerViewAdapter(viewModel.lists)
//
//        binding.listsRecyclerview.adapter = recyclerViewAdapter
//
//        viewModel.onListAdded = {
//            recyclerViewAdapter.listsUpdated()
//        }
//    }


}