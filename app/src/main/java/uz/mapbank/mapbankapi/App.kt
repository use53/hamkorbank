package uz.mapbank.mapbankapi

import android.app.Application
import android.content.Context

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        instanse=this
    }

    fun setLanguagePref(localeKey:String){
        val pref=getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit()
        pref.putString(LOCALE,localeKey)
        pref.apply()
    }

    fun getLanguagePref(): String? {
        val pref=getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return pref.getString(LOCALE,"en")
    }
    companion object{
        var instanse:App?=null
        const val PREFS:String="SHARED_PREFS"
        const val LOCALE:String="LOCALE"
    }
}