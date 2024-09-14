package uz.market.market.activity

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.project1762.Helper.ManagmentCart
import uz.market.market.Adapter.SliderAdapter
import uz.market.market.Model.ItemsModel
import uz.market.market.Model.SliderModel
import uz.market.market.R
import uz.market.market.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding:ActivityDetailBinding
    private lateinit var item:ItemsModel
    private var numberOrder=1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart=ManagmentCart(this)

        getBundle()
        banners()

    }

    private fun banners() {
        val sliderItem=ArrayList<SliderModel>()
        for (imageUrl in item.picUrl){
            sliderItem.add(SliderModel(imageUrl))
        }

        binding.slider.adapter=SliderAdapter(sliderItem,binding.slider)
        binding.slider.clipToPadding = false
        binding.slider.clipChildren = false
        binding.slider.offscreenPageLimit = 3
        binding.slider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun getBundle() {
        item=intent.getParcelableExtra("object")!!

        binding.titleTxt.text=item.title
        binding.descriptionTxt.text=item.description
        binding.priceTxt.text="$"+item.price
        binding.ratingTxt.text="${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener{
            item.numberInCart=numberOrder
            managmentCart.insertFood(item)
        }
        binding.backBtn.setOnClickListener { finish() }
        binding.cartBtn.setOnClickListener {

        }
    }
}