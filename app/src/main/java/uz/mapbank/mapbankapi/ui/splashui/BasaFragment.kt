package uz.mapbank.mapbankapi.ui.splashui

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.common.lazyFast
import uz.mapbank.mapbankapi.utils.preferense.PreferencesImpl

abstract class BasaFragment(@LayoutRes layout:Int):Fragment(layout) {

    val preferense by lazyFast { PreferencesImpl.instanse(requireContext()) }

    val navController by lazy {
        Navigation.findNavController(requireActivity(),
            R.id.splash_fragment) }
      val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(requireActivity(),R.anim.from_bottom_anim) }

    val fromBottomSplash: Animation by lazy {
        AnimationUtils.loadAnimation(requireActivity(),R.anim.from_bottom_anim) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
     fun onBack() {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }

            })
    }
}