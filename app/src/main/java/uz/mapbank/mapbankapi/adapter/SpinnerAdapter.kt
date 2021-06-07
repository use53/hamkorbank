package uz.mapbank.mapbankapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import uz.mapbank.mapbankapi.databinding.ItemViewBinding
import uz.mapbank.mapbankapi.room.HistoryDB

class SpinnerAdapter(val list:MutableList<HistoryDB>):BaseAdapter() {

    override fun getCount(): Int=list.size

    override fun getItem(position: Int): HistoryDB=
        list[position]

    override fun getItemId(position: Int): Long=position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view=ItemViewBinding.inflate(LayoutInflater.from(parent!!.context),parent,false)
        return view.root
    }
}