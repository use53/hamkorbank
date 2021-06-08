package uz.mapbank.mapbankapi

import android.content.Context
import android.content.res.Configuration
import java.util.*

object LocaleManager {

    fun setLocal(mContext:Context): Context? {
        return if (App.instanse!!.getLanguagePref()!=null){
            App.instanse!!.getLanguagePref()?.let { updateResources(mContext, it) }
        }else{
            mContext
        }

    }

    fun setNewLocale(mContext:Context,language:String):Context{
        App.instanse!!.setLanguagePref(language)
        return updateResources(mContext,language)
    }

    private fun updateResources(mContext: Context, languagePref: String): Context {
      var localeContext=mContext
        val locale=Locale(languagePref)
        Locale.setDefault(locale)
        val res=mContext.resources
        val config=Configuration(res.configuration)
        config.setLocale(locale)
        localeContext=mContext.createConfigurationContext(config)
        return localeContext
    }
}