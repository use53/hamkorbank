package uz.mapbank.mapbankapi.ui.payment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.budiyev.android.codescanner.*
import com.google.zxing.Result
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.databinding.FragmentQrcodeBinding
import uz.mapbank.mapbankapi.ui.home.MainBaseFragment

class FragmentQrCode:MainBaseFragment(R.layout.fragment_qrcode) {

    private lateinit var codeScanner: CodeScanner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // val binding=FragmentQrcodeBinding.bind(view)
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                Toast.makeText(activity, it.text, Toast.LENGTH_LONG).show()
            }
        }
        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }
    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}