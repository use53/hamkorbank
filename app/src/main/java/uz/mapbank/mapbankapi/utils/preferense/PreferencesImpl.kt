package uz.mapbank.mapbankapi.utils.preferense

import android.content.Context
import android.content.SharedPreferences
private const val KEY_NAME="KEY_NAME"
private const val KEY_PASSWORD="KEY_PASSWORD"
private const val KEY_CALLNUMBER="KEY_CALLNUMBER"
private const val KEY_CORRENT_FIRST="KEY_CORRENT_FIRST"
private const val KEY_CODE="KEY_CODE"
private const val CODE_CORRENT="CODE_CORRENT"


class PreferencesImpl(preferences: SharedPreferences):IPreferences {
    override var isName: String by TextPreference(preferences, KEY_NAME)
    override var isPassword: String by TextPreference(preferences, KEY_PASSWORD)
    override var isCallNumber: String by TextPreference(preferences, KEY_CALLNUMBER)
    override var isCorrentFirst: Boolean by TextCorrent(preferences, KEY_CORRENT_FIRST)
    override var isCode: String by TextPreference(preferences, KEY_CODE)
    override var codeCorrent: Boolean by TextCorrent(preferences, CODE_CORRENT)


    companion object{
        private var instanse:IPreferences?=null

        fun instanse(ctx: Context): IPreferences {
            if (instanse==null){
                val preference= androidx.preference.PreferenceManager.getDefaultSharedPreferences(ctx)
                instanse=PreferencesImpl(preference)
            }
            return instanse!!
        }

    }
}