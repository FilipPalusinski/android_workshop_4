package com.example.gdgandroidwebinar4.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
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

       // viewModel.getPonyByUrl()

        viewModel.getPonyWithRetrofit()

        searchEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                s?.let{ text ->
                    if(text.isEmpty()){
                        viewModel.getPonyWithRetrofit()
                    }else{
                        viewModel.getPonyWithRetrofit(text.toString())
                    }

                }
            }

        })

    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
