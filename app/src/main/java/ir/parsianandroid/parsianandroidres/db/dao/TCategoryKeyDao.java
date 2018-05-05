package ir.parsianandroid.parsianandroidres.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ir.parsianandroid.parsianandroidres.db.entity.TCategory;
import ir.parsianandroid.parsianandroidres.db.entity.TCategoryKey;
import ir.parsianandroid.parsianandroidres.db.entity.TProducts;


/**
 * Created by JavAd on 2018/02/08.
 */
@Dao
public interface TCategoryKeyDao {
    @Query("SELECT * FROM TCategoryKey")
    List<TCategoryKey> getAll();

    @Query("SELECT * FROM TCategoryKey WHERE Title LIKE :name LIMIT 1")
    TCategoryKey findByName(String name);
    @Query("SELECT * FROM TCategoryKey WHERE GoodCode=:Code LIMIT 1")
    TCategoryKey findByGoodCode(long Code);

    @Query("SELECT COUNT(*) from TCategoryKey")
    int getCount();

    @Insert
    void insertAll(List<TCategoryKey> items);

    @Update
    void update(TCategoryKey item);

    @Delete
    void delete(TCategoryKey item);

    @Query("delete FROM TCategoryKey")
    void ClearTable();
}
