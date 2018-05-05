package ir.parsianandroid.parsianandroidres.Global;

/**
 * Created by JaVAd on 2017/06/22.
 */

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;


public final class FontsOverride {

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName,int fontsize) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),fontAssetName);

        replaceFont(staticTypefaceFieldName, regular,fontsize);
    }



    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface,int fontsize) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
