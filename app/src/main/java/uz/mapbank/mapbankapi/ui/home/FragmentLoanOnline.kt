package uz.mapbank.mapbankapi.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.adapter.PaymentAdapter
import uz.mapbank.mapbankapi.databinding.FragmentLoanBinding
import uz.mapbank.mapbankapi.model.PaymentItem

class FragmentLoanOnline:MainBaseFragment(R.layout.fragment_loan) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentLoanBinding.bind(view)
        val adapter= PaymentAdapter()
        binding.loanRec.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )
        binding.loanRec.adapter=adapter
        val paymentItem= PaymentItem().loanOnline()
        adapter.submitList(paymentItem)
    }
}