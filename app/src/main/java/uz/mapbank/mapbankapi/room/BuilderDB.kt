package uz.mapbank.mapbankapi.room

import android.content.Context
import androidx.room.Room
import uz.fintech.uzbankcard.navui.databasae.HistoryDatabase

class BuilderDB {

    companion object{
        private var instanse:HistoryDatabase?=null
        fun instanse(ctx: Context): HistoryDatabase {
          if (instanse==null){
              instanse=Room.databaseBuilder(ctx,HistoryDatabase::class.java,"bankcard.db").build()
          }else{
              instanse
          }
            return instanse!!
        }

    }
}
