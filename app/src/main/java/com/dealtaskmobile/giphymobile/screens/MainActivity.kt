package com.dealtaskmobile.giphymobile.screens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dealtaskmobile.data.UserModel
import com.dealtaskmobile.giphymobile.events.PaginationScrollListener
import com.dealtaskmobile.giphymobile.R
import com.dealtaskmobile.giphymobile.adapters.GifsAdapter
import com.dealtaskmobile.giphymobile.app.App
import com.dealtaskmobile.giphymobile.viewmodel.GifsListViewModel
import com.dealtaskmobile.giphymobile.viewmodel.GifsListViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var vmFactory: GifsListViewModelFactory

    private var vm: GifsListViewModel? = null

    private var recycleListGifs: RecyclerView? = null

    private var searchGifs: SearchView? = null

    private var progressBar: ProgressBar? = null

    private var recycleIsLoad: ProgressBar? = null

    private val adapterListGifs: GifsAdapter = GifsAdapter(this@MainActivity)

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    var page = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as App).appComponent?.inject(this)
        initRecycle()
        initVM()
        initSearchView()
        vm?.sendQueryGetGifs()
    }

    private fun initRecycle(){
        progressBar = findViewById(R.id.progressBar)
        recycleIsLoad = findViewById(R.id.recycleIsLoad)

        recycleListGifs = findViewById(R.id.recycleListGifs)
        recycleListGifs?.adapter = adapterListGifs
        recycleListGifs?.setHasFixedSize(true)
        recycleListGifs?.layoutManager =  GridLayoutManager(this@MainActivity,2)

        adapterListGifs.setOnItemClickListener(object : GifsAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                var intent = Intent(this@MainActivity, InfoGifActivity::class.java)
                var model = adapterListGifs.getItemFromPosition(position)
                if (model.userModel == null){
                    model.userModel = UserModel("IS_NULL","","","",false)
                }
                intent.putExtra("info_about_gif",model)
                startActivity(intent)
            }
        })

        recycleListGifs?.addOnScrollListener(object : PaginationScrollListener(layoutManager = recycleListGifs?.layoutManager as GridLayoutManager){
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                showProgressView()
                isLoading = true
                page++
                Handler().postDelayed(Runnable {
                    isLoading = false
                    adapterListGifs.addItemGifs(page = page)
                    hideProgressView()
                },3 * 1000)
            }


        })

    }

    private fun initSearchView(){
        searchGifs = findViewById(R.id.searchGifs)
        searchGifs?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                hideRecycle()
                showProgressLoadView()
                vm?.getSearchResult(query = query!!)
                return false
            }
        })
    }

    private fun showProgressView() {
        progressBar!!.visibility = View.VISIBLE
    }

    private fun hideProgressView() {
        progressBar!!.visibility = View.INVISIBLE
    }

    private fun showProgressLoadView() {
        recycleIsLoad!!.visibility = View.VISIBLE
    }

    private fun hideProgressLoadView() {
        recycleIsLoad!!.visibility = View.INVISIBLE
    }

    private fun showRecycle(){
        recycleListGifs!!.visibility = View.VISIBLE
    }

    private fun hideRecycle(){
        recycleListGifs!!.visibility = View.GONE
    }

    private fun initVM(){
        vm = ViewModelProvider(this,vmFactory).get(GifsListViewModel::class.java)
        vm?.getItemsGifs()?.observe(this, Observer {
            hideRecycle()
            showProgressLoadView()
            adapterListGifs.clearData()
            page = 0
            if (!it.dataGifsList.isEmpty()){
                adapterListGifs.addAllGifs(it)
                adapterListGifs.addItemGifs(page=page)
            }
            showRecycle()
            hideProgressLoadView()
        })
    }




}