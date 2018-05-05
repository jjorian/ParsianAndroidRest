package ir.parsianandroid.parsianandroidres.Global;

import android.app.Application;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import ir.parsianandroid.parsianandroidres.db.AppDatabase;

/**
 * Created by JavAd on 2018/02/08.
 */

public class ResApplication extends Application {
    protected static Context context = null;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();

        AppDatabase.getInstance(getApplicationContext());
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Benyamin.ttf",12);
        //MultiDex.install(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    public static Context getContext() {
        return context;
    }
}
