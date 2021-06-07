package uz.mapbank.mapbankapi.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.invan.reduz.business.utils.Event
import uz.mapbank.mapbankapi.utils.preferense.PreferencesImpl
import java.lang.StringBuilder

class CodeSaveViewModel(app:Application):AndroidViewModel(app) {
 /*   private val list= mutableListOf<Int>()
    private var stringBuilder=StringBuilder()
    private val _liveAddList= MutableLiveData<Int>()
    private val _codeLd=MutableLiveData<String>()
    private val preferense=PreferencesImpl.instanse(app.applicationContext)
    fun  listClear(){
        stringBuilder= StringBuilder()
        list.clear()
    }

    fun listAdd(position:Int){
        list.add(position)
        val stringBuilderLenhg=stringBuilder.append(stringBuilder.length)
        _liveAddList.postValue(stringBuilderLenhg.length)
    }
    val liveAddList:LiveData<Int>
    get() = _liveAddList

   fun onReadCode(){
       val codes=list.joinToString("")
       _codeLd.value=codes
   }
    fun saveCode(){
        val codes=list.joinToString("")
        preferense.isCode=codes
    }
    fun onClearCode(){
        _codeLd.value=""
    }

    val codeLd:LiveData<String>
    get() =_codeLd*/

     private val _clickItem=MutableLiveData<Event<String>>()

    fun codeItem(code:String){
       _clickItem.postValue(Event(code))
    }
    val clickItem:LiveData<Event<String>>
    get() = _clickItem


}