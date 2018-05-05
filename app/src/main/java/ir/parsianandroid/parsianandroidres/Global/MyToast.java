package ir.parsianandroid.parsianandroidres.Global;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ir.parsianandroid.parsianandroidres.R;


/**
 * Created by JaVAd on 2017/06/12.
 */

public class MyToast {

    public static int LENGTH_SHORT=Toast.LENGTH_SHORT;
    public static int LENGTH_LONG  =Toast.LENGTH_LONG;
    static  Toast instance;
        public static Toast makeText(Context context, String text, int Delay)
    {

        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = li.inflate(R.layout.custom_toast1, null);
        TextView txt= (TextView) layout.findViewById(R.id.custom_myoast_message);
        txt.setText(text);

        instance = new Toast(context);
        instance.setDuration(Delay);
        instance.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
        instance.setView(layout);//setting the view of custom MyToast layout
        instance.show();
        return instance;

    }


}
