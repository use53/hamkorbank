package uz.mapbank.mapbankapi.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.common.toast
import uz.mapbank.mapbankapi.databinding.FragmentAddcardBinding
import uz.mapbank.mapbankapi.ui.viewmodel.AddCardViewModel
import uz.mapbank.mapbankapi.utils.CardExpiryDateFormatWatcher
import uz.mapbank.mapbankapi.utils.CardNumberFormatWatcher

class AddCardFragment:MainBaseFragment(R.layout.fragment_addcard) {
    var cardnum:String?=null
    var cardDate:String?=null
       private val addCardViewModel:AddCardViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=FragmentAddcardBinding.bind(view)

        val cardnumber=object :CardNumberFormatWatcher(binding.edCardNumberAdd){
            override fun afterTextWithoutPattern(cardNumber: String) {
                cardnum=cardNumber
            }}
            binding.edCardNumberAdd.addTextChangedListener(cardnumber)

        val carddate=object :CardExpiryDateFormatWatcher(binding.cardAdd){
            override fun afterTextWithoutPattern(expiredDate: String) {
                cardDate=expiredDate
            }

        }
        binding.cardAdd.addTextChangedListener(carddate)

        binding.addCardItems.setOnClickListener {
            if (cardDate!!.length>3 && cardnum!!.length>15){
                addCardViewModel.onHistorySaveCard(cardnum.toString(),cardDate.toString(),"ipoteka")
                navController.popBackStack()
            }else{
                requireContext().toast("Error")
            }

            }


        }


}