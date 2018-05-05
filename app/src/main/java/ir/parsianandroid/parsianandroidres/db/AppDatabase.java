package ir.parsianandroid.parsianandroidres.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import ir.parsianandroid.parsianandroidres.db.dao.TCategoryDao;
import ir.parsianandroid.parsianandroidres.db.dao.TCategoryKeyDao;
import ir.parsianandroid.parsianandroidres.db.dao.TCommentsDao;
import ir.parsianandroid.parsianandroidres.db.dao.TProductsDao;
import ir.parsianandroid.parsianandroidres.db.dao.TTablesDao;
import ir.parsianandroid.parsianandroidres.db.entity.TCategory;
import ir.parsianandroid.parsianandroidres.db.entity.TCategoryKey;
import ir.parsianandroid.parsianandroidres.db.entity.TComments;
import ir.parsianandroid.parsianandroidres.db.entity.TProducts;
import ir.parsianandroid.parsianandroidres.db.entity.TTables;

@Database(entities = {TCategory.class, TProducts.class,TCategoryKey.class, TTables.class, TComments.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "ParsianAndroidRes";
    public abstract TCategoryDao tCategoryDao();
    public abstract TProductsDao tProductDao();
    public abstract TCategoryKeyDao tCategoryKeyDao();
    public abstract TTablesDao tTablesKeyDao();
    public abstract TCommentsDao tCommentsDao();

    //private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDatabase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }


    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){

    }

}
