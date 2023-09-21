package com.example.foodstand.views.fragments.recipe.adapter

import android.graphics.Color
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodstand.data.model.foodRecipeModel.ResultsModel
import com.example.foodstand.databinding.RecipeFoodsItemBinding
import com.example.foodstand.util.ViewExt.convertUrlToImage

class RecipesAdapter(private val listener: OnClickListener)
    : ListAdapter<ResultsModel,RecipesAdapter.MyViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : RecipesAdapter.MyViewHolder {
        val binding = RecipeFoodsItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesAdapter.MyViewHolder, position: Int) {
        getItem(position).run { holder.bind(this) }
    }

    inner class MyViewHolder(private val binding : RecipeFoodsItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        init {
            with(binding){
                root.setOnClickListener {
                    val position = absoluteAdapterPosition
                    if(position != RecyclerView.NO_POSITION)
                        listener.onItemClicked(getItem(position).Id)
                }
            }
        }

        fun bind(resultsModel: ResultsModel) =
            with(binding){
                with(resultsModel){
                    recipeTitleTextView.text = Title
                    subtitleTextView.text = Html.fromHtml(Summary, FROM_HTML_MODE_COMPACT)
                    heartCounterTextView.text = AggregateLikes.toString()
                    timeTextView.text = ReadyInMinutes.toString()
                    recipeImageView.convertUrlToImage(Image)
                    if(Vegan){
                        leafTextView.setTextColor(Color.GREEN)
                        leafImageView.setColorFilter(Color.GREEN)
                    }
                }
            }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<ResultsModel>(){
            override fun areItemsTheSame(oldItem: ResultsModel, newItem: ResultsModel) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: ResultsModel, newItem: ResultsModel) =
                oldItem.Id == newItem.Id
        }
    }
}