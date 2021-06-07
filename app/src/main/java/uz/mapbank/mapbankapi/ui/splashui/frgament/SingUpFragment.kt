package uz.mapbank.mapbankapi.ui.splashui.frgament

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.common.hideKeyboard
import uz.mapbank.mapbankapi.common.toast
import uz.mapbank.mapbankapi.databinding.SingupFragmentBinding
import uz.mapbank.mapbankapi.ui.splashui.BasaFragment
import uz.mapbank.mapbankapi.ui.viewmodel.AddCardViewModel
import uz.mapbank.mapbankapi.utils.CardExpiryDateFormatWatcher
import uz.mapbank.mapbankapi.utils.CardNumberFormatWatcher

class SingUpFragment :BasaFragment(R.layout.singup_fragment){

    private var cardNumbers:String?=null
    private var cardDate:String?=null
    private var corrent=false
    private val addCardViewModel: AddCardViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= SingupFragmentBinding.bind(view)

        binding.toolbarSingup.setNavigationOnClickListener {
            it.hideKeyboard()
            navController.popBackStack()
        }
        binding.rotationImage.setOnClickListener {
            if (corrent){
                binding.rotationImage.rotation=360F
                binding.tlMfoSingup.isGone=true
                binding.tlEmployeeSingup.isGone=true
                corrent=false
            }else{
                binding.rotationImage.rotation=180F
                binding.tlMfoSingup.isGone=false
                binding.tlEmployeeSingup.isGone=false
                corrent=true
            }

        }
        binding.phoneNumber.setText("+998")
        val cardNumberFormatWatcher=object : CardNumberFormatWatcher(binding.edCardNumber){
            override fun afterTextWithoutPattern(cardNumber: String) {
                cardNumbers=cardNumber
               // tvItemCardNumber(cardNumber)
            }
        }
        binding.edCardNumber.addTextChangedListener(cardNumberFormatWatcher)


        val cardExpiryDateFormatWatcher=object : CardExpiryDateFormatWatcher(binding.cardDate){
            override fun afterTextWithoutPattern(expiredDate: String) {
                cardDate=expiredDate
               // itemAddInterval(expiredDate)
            }
        }
        binding.cardDate.addTextChangedListener(cardExpiryDateFormatWatcher)

         binding.btNext.setOnClickListener {
             val name=binding.tlNameSingup.editText!!.text.toString()
             val password=binding.tlPasswordSingup.editText.toString()
             val callnumber=binding.phoneNumber.toString()

                    if (name.length>3 && password.length>4 && cardNumbers!!.length>15 && cardDate!!.length>3){
                        preferense.isName=name
                        preferense.isPassword=password
                        addCardViewModel.onHistorySaveCard(cardNumbers.toString(),cardDate.toString(),"765 352.00 UZS")

                        navController.navigate(R.id.code_save_navigation)

             }else{
                 requireContext().toast(getString(R.string.save_name))
             }

        }
    }

}