package uz.mapbank.mapbankapi.adapter.cardadapter

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.thanel.swipeactionview.SwipeActionView
import me.thanel.swipeactionview.SwipeDirection
import me.thanel.swipeactionview.SwipeGestureListener
import uz.mapbank.mapbankapi.common.hide
import uz.mapbank.mapbankapi.common.show
import uz.mapbank.mapbankapi.databinding.ItemViewBinding
import uz.mapbank.mapbankapi.room.HistoryDB

class CardAdapter:ListAdapter<HistoryDB,CardAdapter.cardVh>(Callback()) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardVh {
        val view =ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return cardVh(view)
    }

    override fun onBindViewHolder(holder: cardVh, position: Int) {
        holder.onBind(getItem(position))
    }

    class cardVh(val view: ItemViewBinding):
            RecyclerView.ViewHolder(view.root){


        fun onBind(item: HistoryDB) {
          Handler().postDelayed({
              view.imageUzsRec.show()
              view.imageUzsProgress.hide()
          },2000)
        }

    }
}

class Callback:DiffUtil.ItemCallback<HistoryDB>(){
    override fun areItemsTheSame(oldItem: HistoryDB, newItem: HistoryDB): Boolean =oldItem==newItem


    override fun areContentsTheSame(oldItem: HistoryDB, newItem: HistoryDB): Boolean =
            oldItem.dbid==newItem.dbid

}