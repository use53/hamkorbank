package uz.mapbank.mapbankapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.mapbank.mapbankapi.databinding.ItemPaymentBinding
import uz.mapbank.mapbankapi.model.Payment

class PaymentAdapter :ListAdapter<Payment,
        PaymentAdapter.PaymentVh>(PaymentCallback()){


   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentVh {
     val view=ItemPaymentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
      return PaymentVh(view)
   }

   override fun onBindViewHolder(holder: PaymentVh, position: Int) {
      holder.onBind(getItem(position))
   }
   class PaymentVh(val view:ItemPaymentBinding):RecyclerView.ViewHolder(view.root){
      fun onBind(item: Payment) {
         view.tvPayment.text=item.paymentName
      }

   }
}

class PaymentCallback(): DiffUtil.ItemCallback<Payment>(){
   override fun areItemsTheSame(oldItem: Payment, newItem: Payment): Boolean =
      oldItem==newItem

   override fun areContentsTheSame(oldItem: Payment, newItem: Payment): Boolean =
      oldItem.id==newItem.id

}