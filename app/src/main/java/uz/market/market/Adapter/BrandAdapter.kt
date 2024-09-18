package uz.market.market.Adapter

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.market.market.Model.BrandModel
import uz.market.market.R
import uz.market.market.activity.ListItemsActivity
import uz.market.market.databinding.ViewholderBrandBinding

class BrandAdapter(val items: MutableList<BrandModel>) :
    RecyclerView.Adapter<BrandAdapter.ViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class ViewHolder(val binding: ViewholderBrandBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        holder.binding.title.setTextColor(context.resources.getColor(R.color.white))
        if (selectedPosition == position) {
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.white))
            )

            holder.binding.title.visibility = View.VISIBLE
        } else {
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.black))
            )

            holder.binding.title.visibility = View.GONE
        }

        holder.binding.root.setOnClickListener {
            val position = position
            if (position != RecyclerView.NO_POSITION) {
                lastSelectedPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(holder.itemView.context, ListItemsActivity::class.java).apply {
                    putExtra("id", item.id.toString())
                    putExtra("title", item.title)
                }
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }, 1000)
        }
    }

    override fun getItemCount(): Int = items.size
}