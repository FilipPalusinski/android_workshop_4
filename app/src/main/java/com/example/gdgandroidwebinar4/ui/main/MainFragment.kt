package com.example.gdgandroidwebinar4.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.gdgandroidwebinar4.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private val ponyAdapter = PonyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            adapter = ponyAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }

        viewModel.ponyList.observe(viewLifecycleOwner, Observer { ponyList ->
            ponyAdapter.submitList(ponyList)
        })

        //simple url request example
        //viewModel.getPonyUrlExample()

        //search pony example with retrofit
        viewModel.searchPony(searchEditText.text?.toString())

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchPony(s?.toString())
            }
        })

        viewModel.searchInProgress.observe(viewLifecycleOwner, Observer { inProgress ->
            progressBar.isVisible = inProgress
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
