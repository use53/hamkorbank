package uz.mapbank.mapbankapi.room

import androidx.room.*


@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historyDB:HistoryDB)

    @Query("select * from HistoryDB")
    suspend fun loadAll(): List<HistoryDB>

    //@Delete()
    //fun delete(user:HistoryDB):Completable

    //@Query("select * from HistoryDB where cardnumdb=:cardnum")
    //fun loadFirst(cardnum: String): Single<HistoryDB>

}