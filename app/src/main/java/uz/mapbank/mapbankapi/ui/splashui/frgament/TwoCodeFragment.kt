package uz.mapbank.mapbankapi.ui.splashui.frgament

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.nirigo.mobile.view.passcode.PasscodeView
import com.nirigo.mobile.view.passcode.models.PasscodeItem
import uz.mapbank.mapbankapi.ActivtyCard
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.adapter.codeadapter.PasscodeAdapter
import uz.mapbank.mapbankapi.common.*
import uz.mapbank.mapbankapi.databinding.FragmentTwocodeBinding
import uz.mapbank.mapbankapi.ui.splashui.BasaFragment
import uz.mapbank.mapbankapi.ui.viewmodel.AddCardViewModel
import uz.mapbank.mapbankapi.ui.viewmodel.CodeSaveViewModel
import uz.mapbank.mapbankapi.utils.CardExpiryDateFormatWatcher
import uz.mapbank.mapbankapi.utils.CardNumberFormatWatcher
import java.lang.StringBuilder


class TwoCodeFragment:BasaFragment(R.layout.fragment_twocode), PasscodeView.OnItemClickListener {

    private var twoBinding: FragmentTwocodeBinding?=null
    private val passcodeAdapter by lazyFast { PasscodeAdapter(requireContext()) }
    private var stringBuilder= StringBuilder()
    private val list= mutableListOf<Int>()
    private val codeSaveViewModel: CodeSaveViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val biding=FragmentTwocodeBinding.bind(view)
        twoBinding=biding
        biding.codeImageTwo.startAnimation(fromBottom)
        Handler().postDelayed({
            biding.passcodeViewTwo.show()
            biding.passcodeIndicatorTwo.show()
            biding.codeTvTwo.show()
        },500)

        biding.passcodeViewTwo.adapter=passcodeAdapter
        biding.passcodeViewTwo.setOnItemClickListener(this)
    }

    override fun onItemClick(passcodeView: PasscodeView?,
                             position: Int, p2: View?,
                             p3: Any?) {
        if (!twoBinding!!.passcodeIndicatorTwo.isAnimationInProgress) {
            val passcodeItem = passcodeView!!.adapter.getItem(position)
            if (passcodeItem.type == PasscodeItem.TYPE_CLEAR) {
                twoBinding!!.passcodeIndicatorTwo.clear()
                stringBuilder= StringBuilder()
                list.clear()
            } else if (passcodeItem.type == PasscodeItem.TYPE_NUMBER) {
                list.add(position)
                val stringBuilderLenhg=stringBuilder.append(stringBuilder.length)
                itemListAdd(stringBuilderLenhg.length)

            }
        }
    }

    private fun itemListAdd(length: Int) {
        twoBinding!!.passcodeIndicatorTwo.indicatorLevel = length!!
        if (length == twoBinding!!.passcodeIndicatorTwo.indicatorLength) {
            val codes=list.joinToString("")
            codeSaveViewModel.clickItem.observe(viewLifecycleOwner, {
                if (it.getContentIfNotHandled()==codes){
                    preferense.isCode=codes
                    preferense.codeCorrent=true
                    Intent(requireContext(),ActivtyCard::class.java).apply {
                        startActivity(this)
                    }
                }else{
                    twoBinding!!.passcodeIndicatorTwo.wrongPasscode()
                    requireContext().toast(getString(R.string.code_error))
                    navController.popBackStack()
                }
            })
            requireContext().toast("${codes}")}
    }
/*private var addCardBinding:AddcardFragmentBinding?=null
    private var cardNumbers:String?=null
    private var cardDate:String?=null
    private val addCardViewModel:AddCardViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=AddcardFragmentBinding.bind(view)
        addCardBinding=binding
        val cardNumberFormatWatcher=object :CardNumberFormatWatcher(binding.cardNumber){
            override fun afterTextWithoutPattern(cardNumber: String) {
                cardNumbers=cardNumber
                tvItemCardNumber(cardNumber)
            }
        }
       binding.cardNumber.addTextChangedListener(cardNumberFormatWatcher)

        val cardExpiryDateFormatWatcher=object :CardExpiryDateFormatWatcher(binding.cardDate){
            override fun afterTextWithoutPattern(expiredDate: String) {
                cardDate=expiredDate
              itemAddInterval(expiredDate)
            }
        }
        binding.cardDate.addTextChangedListener(cardExpiryDateFormatWatcher)

        binding.save.setOnClickListener {
            val name="uzCard"
            if (cardNumbers!!.length>15 && cardDate!!.length>3){
                addCardViewModel.onHistorySaveCard(cardNumbers.toString(),cardDate.toString(),name)
                navController.navigate(R.id.code_save_navigation)
            }else{
                requireContext().toast(getString(R.string.card_date_save))
            }
        }
    }
    private fun tvItemCardNumber(text: String) {
        val textParsed = cardNumber(text)
        addCardBinding!!.tvCardnumber.text = textParsed
    }

    private fun itemAddInterval(text: String) {
        val textParsed = cardDateUtils(text)
        addCardBinding!!.tvDate.text = textParsed
    }*/
}


