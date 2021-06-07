package uz.mapbank.mapbankapi.ui.splashui.frgament

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.animation.addListener
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.databinding.SplashFragmentBinding
import uz.mapbank.mapbankapi.ui.splashui.BasaFragment

class SplashFragment:BasaFragment(R.layout.splash_fragment) {
    private lateinit var anim: AnimatorSet
    private var start: Float = 0.7F
    private val end: Float = 1.2F
    private var shouldRepeat =true
    private var splashBinding:SplashFragmentBinding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=SplashFragmentBinding.bind(view)
        splashBinding=binding
        startAnimation(end)
    }

    private fun startAnimation(end: Float) {
        val view = splashBinding!!.splashImage
        anim = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(view, View.SCALE_X, end),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, end),
            )
        }
        anim.duration = 1500
        val addListener = anim.addListener(
            onEnd = {
                if (shouldRepeat) {
                    startAnimation(if (end == start) end else start)
                    shouldRepeat=false
                } else {
                    if (preferense.isCorrentFirst){
                        navController.navigate(R.id.code_save_navigation)
                    }else{
                            navController.navigate(R.id.singin_navigation)

                    }

                }
            }
        )
        anim.start()
    }
}