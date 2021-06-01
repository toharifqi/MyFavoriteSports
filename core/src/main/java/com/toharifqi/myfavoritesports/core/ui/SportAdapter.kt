package com.toharifqi.myfavoritesports.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.toharifqi.myfavoritesports.core.R
import com.toharifqi.myfavoritesports.core.databinding.ItemListSportBinding
import com.toharifqi.myfavoritesports.core.domain.model.Sport
import java.util.ArrayList


class SportAdapter: RecyclerView.Adapter<SportAdapter.ListViewHolder>(){

    private var listData = ArrayList<Sport>()
    var onItemClick: ((Sport) -> Unit)? = null

    fun setData(newListData: List<Sport>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_sport, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val sport = listData[position]
        holder.bind(sport)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemListSportBinding.bind(itemView)
        fun bind(sport: Sport){
            with(binding){
                textName.text = sport.name
                textFormat.text = sport.format
                textDesc.text = sport.description
                if (sport.isFavorite){
                    imgStar.setImageResource(R.drawable.starred)
                }else{
                    imgStar.setImageResource(R.drawable.unstarred)
                }
                Glide.with(itemView.context)
                    .load(sport.thumb)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgSport)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }
}