package uz.mapbank.mapbankapi.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.adapter.PaymentAdapter
import uz.mapbank.mapbankapi.databinding.FragmentMoneyBinding
import uz.mapbank.mapbankapi.model.PaymentItem

class FragmentMoney:MainBaseFragment(R.layout.fragment_money) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* val binding=FragmentMoneyBinding.bind(view)
        val adapter= PaymentAdapter()
        binding.moneyRec.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )
        binding.moneyRec.adapter=adapter
        val paymentItem= PaymentItem().loanOnline()
        adapter.submitList(paymentItem)*/
    }
}