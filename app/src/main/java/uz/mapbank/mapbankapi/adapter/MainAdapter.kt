package uz.mapbank.mapbankapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.mapbank.mapbankapi.databinding.ItemMainBinding
import uz.mapbank.mapbankapi.model.MainModel
import uz.mapbank.mapbankapi.onClick.IonListenerMain


class MainAdapter(val ionListenerMain: IonListenerMain): ListAdapter<MainModel,MainAdapter.MainVH>(MainCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainVH {
        val view =ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainVH(view,ionListenerMain)
    }

    override fun onBindViewHolder(holder: MainVH, position: Int) {
        holder.onBind(getItem(position))
    }
    class MainVH(val view: ItemMainBinding, ionListenerMain: IonListenerMain)
        :RecyclerView.ViewHolder(view.root){

        init {
            itemView.setOnClickListener {
                ionListenerMain.onClick(adapterPosition)
            }
        }
        fun onBind(item: MainModel) {
           /* MaterialRippleLayout.on(view.root)
                .rippleColor(Color.BLACK)
                .create();*/
            view.image.setImageResource(item.image)
            view.tvTitle.text=item.title
        }

    }
}

class MainCallback():DiffUtil.ItemCallback<MainModel>(){
    override fun areItemsTheSame(oldItem: MainModel, newItem: MainModel): Boolean =
            oldItem==newItem

    override fun areContentsTheSame(oldItem: MainModel, newItem: MainModel): Boolean =
            oldItem.id==newItem.id

}