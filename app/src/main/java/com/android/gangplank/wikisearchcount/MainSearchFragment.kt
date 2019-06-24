package com.android.gangplank.wikisearchcount


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.gangplank.wikisearchcount.room.SearchHistoryData
import com.android.gangplank.wikisearchcount.room.entities.SearchHistoryDataBase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MainSearchFragment : Fragment() {

    private val wikiApiService by lazy {
        WikiApiService.create()
    }
    private var disposable: Disposable? = null
    private var mDb: SearchHistoryDataBase? = null
    private var count: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mDb = SearchHistoryDataBase.getInstance(context!!)
        val view = inflater.inflate(R.layout.fragment_main_search, container, false)

        val historyFab = view.findViewById<FloatingActionButton>(R.id.historyFab)
        historyFab.setOnClickListener { fab: View ->
            view.findNavController()
                .navigate(MainSearchFragmentDirections.actionMainSearchFragmentToSearchHistoryFragment())
        }

        val searchBtn = view.findViewById<Button>(R.id.searchBtn)
        val searchText = view.findViewById<EditText>(R.id.searchTextEt)
        searchBtn.setOnClickListener { button: View ->
            val searchSequence = searchText.text.toString()
            beginSearch(searchSequence)
        }

        val addToDb = view.findViewById<Button>(R.id.addToDb)
        addToDb.setOnClickListener { button: View ->
            val searchSequence = searchText.text.toString()
            if (count != null && searchSequence.isNotEmpty())
                mDb!!.searchHistoryDataDao().addOne(SearchHistoryData(null, searchSequence, count!!))
        }

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.main_search_frag_action_bar_title)

        return view
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun beginSearch(srsearch: String) {
        disposable = wikiApiService.hitCountCheck("query", "json", "search", srsearch)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    run {
                            Log.i("MY_TAG", result.toString())
                            showResult(result.query.searchinfo.totalhits)
                        }
                },
                { error -> showError(error) }
            )
    }

    private fun showResult(count: Int) {
        val result = "$count result found"
        view?.findViewById<TextView>(R.id.searchResultTv)?.text = result
        this.count = count
    }

    private fun showError(error: Throwable) {
        Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
        error.printStackTrace()
    }
}
