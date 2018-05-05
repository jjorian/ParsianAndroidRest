package ir.parsianandroid.parsianandroidres.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ir.parsianandroid.parsianandroidres.Models.ProductCategory;
import ir.parsianandroid.parsianandroidres.db.entity.TProducts;
import ir.parsianandroid.parsianandroidres.db.entity.TTables;


/**
 * Created by JavAd on 2018/02/08.
 */
@Dao
public interface TTablesDao {
    @Query("SELECT * FROM TTables")
    List<TTables> getAll();

    @Query("SELECT * FROM TTables WHERE Code=:code")
    TTables getTable(int code);

    @Query("SELECT * FROM TTables WHERE Title LIKE :name LIMIT 1")
    TTables findByName(String name);


    @Query("SELECT COUNT(*) from TTables")
    int getCount();

    @Insert
    void insertAll(List<TTables> items);

    @Update
    void update(TTables item);

    @Query("update TTables set Status= :status where Code= :code")
    void updateStatus(int status,int code);

    @Delete
    void delete(TTables item);

    @Query("delete FROM TTables")
    void ClearTable();
}
