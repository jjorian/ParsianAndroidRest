package ir.parsianandroid.parsianandroidres.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ir.parsianandroid.parsianandroidres.db.entity.TCategory;


/**
 * Created by JavAd on 2018/02/08.
 */
@Dao
public interface TCategoryDao {
    @Query("SELECT * FROM TCategory")
    List<TCategory> getAll();

    @Query("SELECT * FROM TCategory WHERE Title LIKE :title LIMIT 1")
    TCategory findByName(String title);

    @Query("SELECT COUNT(*) from TCategory")
    int countCategory();

    @Insert
    void insertAll(List<TCategory> categories);

    @Update
    void update(TCategory product);

    @Delete
    void delete(TCategory product);

    @Query("delete FROM TCategory")
    void ClearTable();
}
