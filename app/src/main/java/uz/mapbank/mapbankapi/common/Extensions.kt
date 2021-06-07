package uz.mapbank.mapbankapi.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


fun <T> lazyFast(
        mode: LazyThreadSafetyMode = LazyThreadSafetyMode.NONE,
        initializer: () -> T
): Lazy<T> = lazy(mode, initializer)


fun cardNumber(text: String): String {

    val textParsed = when {
        text.length >= 12 -> {
            text.substring(0, 4) + "   " + text.substring(4, 8) +
                    "   " + text.subSequence(8, 12) + "   " + text.substring(12)
        }
        text.length >= 8 -> {
            text.substring(0, 4) + "   " + text.substring(4, 8) + "   " + text.substring(8)
        }
        text.length >= 4 -> {
            text.substring(0, 4) + "    " + text.substring(4)
        }
        else -> text

    }
    return textParsed
}


fun cardDateUtils(text: String): String {
    val textParsed = when {
        text.length >= 3 -> {
            text.substring(0, 2) + "  /  " + text.substring(2)
        }
        else -> text
    }
    return textParsed
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun Context.toast(msg:String){
    Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

