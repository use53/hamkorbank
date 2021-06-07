package uz.fintech.uzbankcard.navui.databasae

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.mapbank.mapbankapi.room.HistoryDB
import uz.mapbank.mapbankapi.room.HistoryDao


@Database(entities = [HistoryDB::class],version = 1,exportSchema = false)
abstract class HistoryDatabase:RoomDatabase(){
    abstract fun getCardDao(): HistoryDao

}