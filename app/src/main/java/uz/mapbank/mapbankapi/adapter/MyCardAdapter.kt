package uz.mapbank.mapbankapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.mapbank.mapbankapi.adapter.cardadapter.Callback
import uz.mapbank.mapbankapi.databinding.ItemMycardBinding
import uz.mapbank.mapbankapi.room.HistoryDB

class MyCardAdapter:ListAdapter<HistoryDB,MyCardAdapter.MyCardVH>(Callback()) {





    class MyCardVH(view:ItemMycardBinding):RecyclerView.ViewHolder(view.root){

        fun onBind(item: HistoryDB) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardVH {
        val view=ItemMycardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
         return MyCardVH(view)
    }

    override fun onBindViewHolder(holder: MyCardVH, position: Int) {
        holder.onBind(getItem(position))
    }
}

/*
class MyCardCallback(): DiffUtil.ItemCallback<MyCard>(){
    override fun areItemsTheSame(oldItem: MyCard, newItem: MyCard): Boolean =
        oldItem==newItem

    override fun areContentsTheSame(oldItem:MyCard, newItem: MyCard): Boolean =
        oldItem.id==newItem.id

}*/
