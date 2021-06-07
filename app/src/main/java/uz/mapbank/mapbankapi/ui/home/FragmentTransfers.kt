package uz.mapbank.mapbankapi.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.adapter.SpinnerAdapter
import uz.mapbank.mapbankapi.databinding.FragmentTransfersBinding
import uz.mapbank.mapbankapi.ui.viewmodel.HomeViewModel

class FragmentTransfers:MainBaseFragment(R.layout.fragment_transfers) {

    val homeViewModel:HomeViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentTransfersBinding.bind(view)
        homeViewModel.cardHistory.observe(viewLifecycleOwner, Observer {
            val adapter=SpinnerAdapter(it)
            binding.spinner.adapter=adapter
        })

    }
}