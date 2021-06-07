package uz.mapbank.mapbankapi.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class HistoryDB(
        val cardNumber: String,
        val cardDate:String,
        val cardName:String,
        @PrimaryKey(autoGenerate = true)
        val dbid: Long = 0L)