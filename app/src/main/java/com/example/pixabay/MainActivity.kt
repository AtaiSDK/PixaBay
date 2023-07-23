package com.example.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.pixabay.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var page = 1
    var isAddToList = false
    var adapter = ImageAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickers()
        onScroll()
    }

    private fun onScroll() {
        val scrollListener = object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!recyclerView.canScrollVertically(1)){
                    isAddToList = true
                    ++page
                    getImage()
                }
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

            }
        }
        binding.recyclerView.addOnScrollListener(scrollListener)
    }
    private fun initClickers(){
        with(binding){
            searchBtn.setOnClickListener {
            isAddToList = false
                getImage()
            }
        }
    }
    private fun getImage() {
        with(binding) {
            RetrofitService.api.getPictures(wordEd.text.toString(), page = page)
                .enqueue(object : Callback<PixaModel> {
                    override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                        if (response.isSuccessful) {
                            recyclerView.adapter = adapter
                            if(!isAddToList){
                                adapter.list.clear()
                            }
                            adapter.list.addAll(response.body()?.hits!!)
                        }
                    }

                    override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }
}