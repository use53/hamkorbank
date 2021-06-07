package uz.mapbank.mapbankapi.ui.splashui.frgament

import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.View

import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.common.lazyFast
import uz.mapbank.mapbankapi.common.show
import uz.mapbank.mapbankapi.common.toast
import uz.mapbank.mapbankapi.databinding.SinginFragmentBinding
import uz.mapbank.mapbankapi.ui.splashui.BasaFragment
import uz.mapbank.mapbankapi.utils.preferense.PreferencesImpl

class SingInFragment:BasaFragment(R.layout.singin_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= SinginFragmentBinding.bind(view)

         onBack()
        Handler().postDelayed({
            binding.tvWelcome.show()
            binding.tvRegister.show()
            binding.splashAccount.show()
            binding.tlPasswordSingin.show()
            binding.login.show()
            binding.tlNameSingin.show()
          },500)
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
}