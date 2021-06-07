package uz.mapbank.mapbankapi.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.adapter.MyCardAdapter
import uz.mapbank.mapbankapi.databinding.FragmentMycardBinding
import uz.mapbank.mapbankapi.ui.viewmodel.HomeViewModel

class MyCardFragment:MainBaseFragment(R.layout.fragment_mycard) {

    val homeViewModel:HomeViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val biding=FragmentMycardBinding.bind(view)
        val myCardAdapter=MyCardAdapter()
        biding.mycardRec.adapter=myCardAdapter
        homeViewModel.cardHistory.observe(viewLifecycleOwner, Observer {
            myCardAdapter.submitList(it)
        })
        biding.addCard.setOnClickListener {
            navController.navigate(R.id.addcard_nav)
        }


    }
}