package uz.mapbank.mapbankapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.mapbank.mapbankapi.databinding.ItemNewsBinding
import uz.mapbank.mapbankapi.model.News

class NewsAdapter:ListAdapter<News,NewsAdapter.NewsVh>(NewsCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVh {
        val view=ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsVh(view)
    }

    override fun onBindViewHolder(holder: NewsVh, position: Int) {
        holder.onBind(getItem(position))
    }
    class NewsVh(val view:ItemNewsBinding):RecyclerView.ViewHolder(view.root){
        fun onBind(item: News) {
            view.newsDate.text=item.dateTime
            view.newsTitle.text=item.title
            view.newsText.text=item.text
        }

    }
}

class NewsCallback:DiffUtil.ItemCallback<News>(){
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean=
        oldItem==newItem

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem.id==newItem.id

}