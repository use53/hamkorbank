package uz.mapbank.mapbankapi.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.common.lazyFast
import uz.mapbank.mapbankapi.model.MainModel
import uz.mapbank.mapbankapi.room.BuilderDB
import uz.mapbank.mapbankapi.room.HistoryDB

class HomeViewModel(app:Application):AndroidViewModel(app) {

    private val db by lazyFast { BuilderDB.instanse(app.applicationContext) }
    private val _cardHistory=MutableLiveData<MutableList<HistoryDB>>()

    fun cardListRead(){
    viewModelScope.launch(Dispatchers.IO) {
       val historyDB= db.getCardDao().loadAll()
        _cardHistory.postValue(historyDB as MutableList<HistoryDB>?)
    }
    }
    val cardHistory:LiveData<MutableList<HistoryDB>>
    get() = _cardHistory

    fun mainList(): MutableList<MainModel> {
        val list= mutableListOf<MainModel>().apply {
            add(MainModel(R.drawable.transfers_24,"Transfers"))
            add(MainModel(R.drawable.repayment_home_24,"Loan \n repayment"))
            add(MainModel(R.drawable.money_monetization_24,"money\n transfer"))
            add(MainModel(R.drawable.maps_home_24,"Maps"))
            add(MainModel(R.drawable.payments_24,"Payments"))
            add(MainModel(R.drawable.ic_baseline_contact_mail_24,"Online \n contribution"))
            add(MainModel(R.drawable.travel_explore_24,"Online \n exchange"))
            add(MainModel(R.drawable.mycard_credit_card_24,"Virtual card"))
        }
        return list

    }
}