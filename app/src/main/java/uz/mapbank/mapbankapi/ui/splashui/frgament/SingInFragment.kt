package uz.mapbank.mapbankapi.ui.splashui.frgament

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.flatdialoglibrary.dialog.FlatDialog
import uz.mapbank.mapbankapi.LocaleManager
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.common.show
import uz.mapbank.mapbankapi.common.toast
import uz.mapbank.mapbankapi.databinding.SinginFragmentBinding
import uz.mapbank.mapbankapi.onClick.IonClickDialog
import uz.mapbank.mapbankapi.ui.splashui.BasaFragment
import uz.mapbank.mapbankapi.ui.splashui.dialog.DialogAler


class SingInFragment:BasaFragment(R.layout.singin_fragment){

    lateinit var dialog:DialogAler
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= SinginFragmentBinding.bind(view)
             binding.lanTv.setOnClickListener {
                 language()
             }
         onBack()
        Handler().postDelayed({
            binding.tvWelcome.show()
            binding.tvRegister.show()
            binding.splashAccount.show()
            binding.tlPasswordSingin.show()
            binding.login.show()
            binding.tlNameSingin.show()
        }, 500)
        binding.login.setOnClickListener {
            val name=binding.tlNameSingin.editText.toString()
            val password=binding.tlPasswordSingin.editText.toString()
          if(name.equals(preferense.isName) && password.equals(preferense.isPassword)){
               preferense.codeCorrent=false
              navController.navigate(R.id.code_save_navigation)
          }else{
               requireContext().toast(getString(R.string.error_name))
          }


       }
        binding.splashImage.startAnimation(fromBottomSplash)
        binding.tvRegister.setOnClickListener {
            navController.navigate(R.id.singup_fragment)
        }


    }

    private fun language() {
       /*val flat=FlatDialog(requireContext())
        flat.setBackgroundColor(R.color.white)
        flat.setTitle("Tilni Tanlang")
        flat.setFirstButtonText("Uzbek")
        flat.setFirstButtonColor(R.color.white)
        flat.setSecondButtonColor(R.color.white)
        flat.setFirstButtonTextColor(R.color.color_two)
        flat.setSecondButtonText("English")
        flat.setSecondButtonTextColor(R.color.color_two)
        flat.withFirstButtonListner{
            LocaleManager.setNewLocale(requireContext(), "uz")
            requireActivity().recreate()
            flat.dismiss()
        }
        flat.withSecondButtonListner {

            LocaleManager.setNewLocale(requireContext(), "en")
            requireActivity().recreate()
            flat.dismiss()
        }
            .setCancelable(true)
        flat.show()*/

        val dialog=    AlertDialog.Builder(requireContext())
            .setItems(R.array.lan_list) { dialog, which ->
                when (which) {
                    0 -> {
                        LocaleManager.setNewLocale(requireContext(), "uz")
                        requireActivity().recreate()
                        dialog.dismiss()
                    }
                    1 -> {
                        LocaleManager.setNewLocale(requireContext(), "en")
                        requireActivity().recreate()
                        dialog.dismiss()
                    }
                }


            }.setCancelable(true)
            .create()
        dialog.show()
    }
    }
