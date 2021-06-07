package uz.mapbank.mapbankapi.ui.home

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import uz.mapbank.mapbankapi.R

abstract class MainBaseFragment(@LayoutRes layout:Int):Fragment(layout) {

    val navController by lazy {
        Navigation.findNavController(requireActivity(),
            R.id.nav_host_fragment) }
}