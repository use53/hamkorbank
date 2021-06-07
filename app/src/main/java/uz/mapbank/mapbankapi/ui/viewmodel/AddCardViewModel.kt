package uz.mapbank.mapbankapi.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.mapbank.mapbankapi.common.lazyFast
import uz.mapbank.mapbankapi.room.BuilderDB
import uz.mapbank.mapbankapi.room.HistoryDB
import uz.mapbank.mapbankapi.utils.preferense.PreferencesImpl

class AddCardViewModel(app:Application):AndroidViewModel(app) {

    val builderDB by lazyFast { BuilderDB.instanse(app.applicationContext) }
    val preferense =PreferencesImpl.instanse(app.applicationContext)
    fun onHistorySaveCard(
        cardnum:String,
        cardDate:String,
        cardname:String){
        viewModelScope.launch(Dispatchers.IO) {
            val historyDB=HistoryDB(cardnum,cardDate,cardname)
            builderDB.getCardDao().insert(historyDB)
            preferense.isCorrentFirst=true
        }
    }
}