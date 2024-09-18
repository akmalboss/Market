package uz.market.market.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import uz.market.market.Adapter.ListItemsAdapter
import uz.market.market.ViewModel.MainViewModel
import uz.market.market.databinding.ActivityListItemsBinding

class ListItemsActivity : BaseActivity() {
    private lateinit var binding: ActivityListItemsBinding
    private val viewModel = MainViewModel()
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getBundle()
        initList()

    }

    private fun initList() {
        binding.apply {
            binding.backBtn.setOnClickListener { finish() }
            progressBarList.visibility= View.VISIBLE
            viewModel.brands.observe(this@ListItemsActivity, Observer {
                viewList.layoutManager= GridLayoutManager(this@ListItemsActivity,2)
//                viewList.adapter= ListItemsAdapter(it)
                progressBarList.visibility= View.GONE
            })
            viewModel.loadFiltered(id)
        }
    }



    private fun getBundle() {
        id= intent.getStringExtra("id")!!
        title=intent.getStringExtra("title")!!

        binding.categoryTxt.text=title
    }
}