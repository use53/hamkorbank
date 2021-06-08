package uz.mapbank.mapbankapi.ui.payment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.adapter.NewsAdapter
import uz.mapbank.mapbankapi.adapter.PaymentAdapter
import uz.mapbank.mapbankapi.databinding.FragmentNewsBinding
import uz.mapbank.mapbankapi.model.PaymentItem
import uz.mapbank.mapbankapi.ui.home.MainBaseFragment

class FragmentNews:MainBaseFragment(R.layout.fragment_news) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentNewsBinding.bind(view)
        val adapter= NewsAdapter()
        binding.newsRec.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )
        binding.newsRec.adapter=adapter
        val paymentItem= PaymentItem().newsItem()
        adapter.submitList(paymentItem)
    }
}