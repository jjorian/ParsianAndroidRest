package ir.parsianandroid.parsianandroidres.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ir.parsianandroid.parsianandroidres.db.entity.TComments;
import ir.parsianandroid.parsianandroidres.db.entity.TTables;


/**
 * Created by JavAd on 2018/02/08.
 */
@Dao
public interface TCommentsDao {
    @Query("SELECT * FROM TComments")
    List<TComments> getAll();

    @Query("SELECT * FROM TComments WHERE ID=:code")
    TComments getItems(int code);

    @Query("SELECT COUNT(*) from TComments")
    int getCount();

    @Insert
    void insertAll(List<TComments> items);

    @Update
    void update(TComments item);

    @Delete
    void delete(TComments item);

    @Query("delete FROM TComments")
    void ClearTable();
}
