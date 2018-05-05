package ir.parsianandroid.parsianandroidres.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ir.parsianandroid.parsianandroidres.Models.ProductCategory;
import ir.parsianandroid.parsianandroidres.db.entity.TProducts;


/**
 * Created by JavAd on 2018/02/08.
 */
@Dao
public interface TProductsDao {
    @Query("SELECT * FROM TProducts")
    List<TProducts> getAll();

    @Query("SELECT * FROM TProducts WHERE Code=:code LIMIT 1")
    TProducts GetProduct(long code);

    @Query("SELECT * FROM TProducts WHERE CName LIKE :name LIMIT 1")
    TProducts findByName(String name);

    @Query("SELECT Code,CName,Title,Price_1,Price_2,Price_3,Price_4,Price_5 FROM TProducts,TCategoryKey where TProducts.Code=TCategoryKey.GoodCode and TCategoryKey.CategoryCode=:tCode and Title like :Search")
    List<ProductCategory> getByCategory(int tCode,String Search);

    @Query("SELECT COUNT(*) from TProducts")
    int getCount();

    @Insert
    void insertAll(List<TProducts> items);

    @Update
    void update(TProducts item);

    @Delete
    void delete(TProducts item);

    @Query("delete FROM TProducts")
    void ClearTable();


}
