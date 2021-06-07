package uz.mapbank.mapbankapi.ui.splashui.frgament

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.nirigo.mobile.view.passcode.PasscodeView
import com.nirigo.mobile.view.passcode.models.PasscodeItem
import uz.mapbank.mapbankapi.ActivtyCard
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.adapter.codeadapter.PasscodeAdapter
import uz.mapbank.mapbankapi.common.lazyFast
import uz.mapbank.mapbankapi.common.show
import uz.mapbank.mapbankapi.common.toast
import uz.mapbank.mapbankapi.databinding.CodeSaveFragmentBinding
import uz.mapbank.mapbankapi.ui.splashui.BasaFragment
import uz.mapbank.mapbankapi.ui.viewmodel.CodeSaveViewModel
import java.lang.StringBuilder

class CodeSaveFragment:BasaFragment(R.layout.code_save_fragment), PasscodeView.OnItemClickListener {

    private val passcodeAdapter by lazyFast { PasscodeAdapter(requireContext()) }
    private var codeBiding:CodeSaveFragmentBinding?=null
    private val list= mutableListOf<Int>()
    private var stringBuilder= StringBuilder()
    private val codeSaveViewModel:CodeSaveViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBack()

        val biding=CodeSaveFragmentBinding.bind(view)
        biding.codeImage.startAnimation(fromBottom)

       Handler().postDelayed({
           biding.passcodeView.show()
           biding.passcodeIndicator.show()
           biding.codeTv.show()
           biding.forgot.show()
       },500)
         codeBiding=biding
        biding.forgot.setOnClickListener {
            navController.navigate(R.id.singin_navigation)
        }
        biding.passcodeView.adapter=passcodeAdapter
        biding.passcodeView.setOnItemClickListener(this)

    }

    override fun onItemClick(passcodeView: PasscodeView?,
                             position: Int, p2: View?,
                             p3: Any?) {

        if (!codeBiding!!.passcodeIndicator.isAnimationInProgress) {
            val passcodeItem = passcodeView!!.adapter.getItem(position)
            if (passcodeItem.type == PasscodeItem.TYPE_CLEAR) {
                codeBiding!!.passcodeIndicator.clear()
                stringBuilder= StringBuilder()
                list.clear()
            } else if (passcodeItem.type == PasscodeItem.TYPE_NUMBER) {
                list.add(position)
                val stringBuilderLenhg=stringBuilder.append(stringBuilder.length)
                itemListAdd(stringBuilderLenhg.length)

            }
        }
    }

    private fun itemListAdd(it: Int?) {
        codeBiding!!.passcodeIndicator.indicatorLevel = it!!
        if (it == codeBiding!!.passcodeIndicator.indicatorLength) {
            val codes=list.joinToString("")
            if (preferense.codeCorrent){
                if (preferense.isCode==codes){
                    Intent(requireContext(),ActivtyCard::class.java).apply {
                        startActivity(this)
                    }
                    requireActivity().finish()
                }else{
                    codeBiding!!.passcodeIndicator.clear()
                    stringBuilder= StringBuilder()
                    list.clear()
                    codeBiding!!.passcodeIndicator.wrongPasscode()
                }
            }else{
               codeSaveViewModel.codeItem(codes)
                codeBiding!!.passcodeIndicator.clear()
                stringBuilder= StringBuilder()
                list.clear()
                navController.navigate(R.id.addcard_navigation)

            }
            //requireContext().toast("${codes}")

            /*if (preferense.isCorrentFirst){
                codeSaveViewModel.onReadCode()
                if(codeSaveViewModel.codeLd.value!! == preferense.isCode){
                    Intent(requireContext(),ActivtyCard::class.java)
                        .apply {
                            startActivity(this)
                        }
                    requireActivity().finish()
                }else{
                    codeBiding!!.passcodeIndicator.clear()
                    codeSaveViewModel.listClear()
                    codeSaveViewModel.onClearCode()
                    requireContext().toast(getString(R.string.error_code))

                }
            }else{
               codeSaveViewModel.saveCode()
                Intent(requireContext(),ActivtyCard::class.java)
                    .apply {
                        startActivity(this)
                    }
                preferense.isCorrentFirst=true
                requireActivity().finish()

            }*/
        }
    }


}