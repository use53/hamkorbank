package uz.mapbank.mapbankapi.ui.splashui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import uz.mapbank.mapbankapi.LocaleManager
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.onClick.IonClickDialog

@Suppress("UNREACHABLE_CODE")
class DialogAler() :DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
      AlertDialog.Builder(requireContext())
            .setItems(R.array.lan_list) { dialog, which ->
                when (which) {
                    0 -> {
                        LocaleManager.setNewLocale(requireContext(), "uz")
                        requireActivity().recreate()
                    }
                    1 -> {
                        LocaleManager.setNewLocale(requireContext(), "en")
                        requireActivity().recreate()
                    }
                }


            }.setCancelable(true)
            .create()



    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}