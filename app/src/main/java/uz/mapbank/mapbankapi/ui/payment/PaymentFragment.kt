package uz.mapbank.mapbankapi.ui.payment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.adapter.PaymentAdapter
import uz.mapbank.mapbankapi.databinding.FragmentPaymentBinding
import uz.mapbank.mapbankapi.model.PaymentItem

class PaymentFragment : Fragment(R.layout.fragment_payment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentPaymentBinding.bind(view)

        val adapter=PaymentAdapter()
        binding.paymentRec.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        binding.paymentRec.adapter=adapter
        val paymentItem=PaymentItem().paymentList()
        adapter.submitList(paymentItem)

    }



}