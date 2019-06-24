package com.android.gangplank.wikisearchcount


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class SearchHistoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.search_history_frag_action_bar_title)
        return inflater.inflate(R.layout.fragment_search_history, container, false)
    }


}
