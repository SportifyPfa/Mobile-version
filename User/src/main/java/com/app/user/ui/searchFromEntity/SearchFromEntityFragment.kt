package com.app.user.ui.searchFromEntity

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.user.R
import com.app.user.adapters.StadiumsAdapter
import com.app.user.databinding.FragmentSearchFromEntityBinding
import com.app.user.model.Stadium
import com.app.user.utils.OnItemSelectedInterface
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SearchFromEntityFragment : Fragment(R.layout.fragment_search_from_entity),
    OnItemSelectedInterface {
    private val args: SearchFromEntityFragmentArgs by navArgs()
    private lateinit var binding: FragmentSearchFromEntityBinding
    private val viewModel: SearchFromEntityViewModel by viewModels()
    private lateinit var stadiumAdapter: StadiumsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentSearchFromEntityBinding.bind(view)
        initUI(binding)
    }

    private fun initUI(binding: FragmentSearchFromEntityBinding) {
        //get Id from args
        val idEntity = args.idEntity

        // Setup our recycler
        binding.stadiumList.apply {
            stadiumAdapter = StadiumsAdapter(context, this@SearchFromEntityFragment)
            adapter = stadiumAdapter
            layoutManager =
                LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        binding.mainButtonSearchFromFragment.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    run {
                        // Set Static Data
                        var s = Stadium(
                            name = "Stadium 1", price = "100", numberOfPlayer = "10",
                            date = "SAT-May 2:00 PM-3:00PM"
                        )
                        var s2 = Stadium(
                            name = "Stadium 2", price = "200", numberOfPlayer = "10",
                            date = "SAT-May 2:00 PM-3:00PM"
                        )
                        var s3 = Stadium(
                            name = "Stadium 4", price = "100", numberOfPlayer = "10",
                            date = "SAT-May 2:00 PM-3:00PM"
                        )
                        var s4 = Stadium(
                            name = "Stadium 3", price = "250", numberOfPlayer = "10",
                            date = "SAT-May 2:00 PM-3:00PM"
                        )
                        var myList: ArrayList<Stadium> = ArrayList()
                        myList.add(s)
                        myList.add(s)
                        myList.add(s2)
                        myList.add(s2)
                        myList.add(s3)
                        myList.add(s3)
                        myList.add(s3)
                        myList.add(s4)
                        myList.add(s4)
                        myList.add(s4)
                        stadiumAdapter.setData(myList)
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE)
            )
            datePicker.show()

        }
    }

    override fun onItemClick(position: Int) {
    }
}