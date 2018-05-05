package ir.parsianandroid.parsianandroidres.Global;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.text.DecimalFormat;

import ir.parsianandroid.parsianandroidres.AppSettings;
import ir.parsianandroid.parsianandroidres.BuildConfig;

/**
 * Created by JavAd on 2018/02/08.
 */

public class GlobalUtils {

    public static final String[] requiredPermissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
    };

    public static String serialDate = "13961226";
    public static String serialCreator()

    {
        //8 digits for yyyy/mm/dd
        // 3 digits for number software 001 parsianandroid 002 parsianandroidwindows 003 parsianandroidres
        //1 digits usertype client=1 admin=0;
        //1 digits userlogined or no 1 logined 0 notlogined
        AppSettings app = new AppSettings(ResApplication.getContext());
        if (app.GetUserName().equals("")) {
            return serialDate + "00310";
        } else {
            return serialDate + "00311" + app.GetUserName();
        }
    }
    public static boolean hasPermissions(@NonNull String... permissions) {

        for (String permission : permissions)
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(ResApplication.getContext(), permission))
                return false;
        return true;
    }
    public static void setupLinerRecycler(RecyclerView mainList, Context ctx,int orientation,int columnsCount) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        mainList.setHasFixedSize(true);
        final RtlGridLayoutManager layoutManager1 = new RtlGridLayoutManager( ctx,  columnsCount,  orientation, false);
        layoutManager1.setOrientation(orientation);
        mainList.setLayoutManager(layoutManager1);
        // First param is number of columns and second param is orientation i.e Vertical or Horizontal


    }

    public static String getCurrecncy(double d)
    {
        DecimalFormat df = new DecimalFormat("#,###.###");
        return df.format(d);

        /*d=Math.round(d);
        BigDecimal b2=BigDecimal.valueOf(d);
        NumberFormat nf=NumberFormat.getInstance();
        return nf.format(b2.doubleValue());*/
    }
    public static void SetFirstSettings(Context c) {
        try {
            String str = Environment.getExternalStorageDirectory().toString() + File.separator + "ParsianAndroidRes";

            File pa = new File(str);

            if (!pa.exists())
                pa.mkdir();
            File pay = new File(pa.getPath() + File.separator + "Webs");
            if (!pay.exists()) {
                pay.mkdir();
            }
            /*File vid = new File(pa.getPath() + File.separator + "Videos");
            if (!vid.exists()) {
                vid.mkdir();
            }

            File factors = new File(pa.getPath() + File.separator + "Factors");
            if (!factors.exists()) {
                factors.mkdir();
            }
            File backup = new File(pa.getPath() + File.separator + "Backups");
            if (!backup.exists()) {
                backup.mkdir();
            }*/
        }catch (Exception ee)
        {
            ee.printStackTrace();
        }


    }
    public static boolean CopyChatWebFile()
    {
        return true;
    }
    public static String ReadyPost(JSONObject obj)
    {
        JSONObject objj=new JSONObject();

        try
        {
            objj.put("data",Compressing.compress(obj.toString()));
        }
        catch(Exception ee)
        {

        }
        return objj.toString();
    }
    public static String getDeviceId() {
        if (ActivityCompat.checkSelfPermission(ResApplication.getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        String deviceId = ((TelephonyManager) ResApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        if (deviceId != null) {
            return deviceId;
        } else {
            return android.os.Build.SERIAL;
        }
    }
    public static String getPriceLabel()
    {
        int index=AppSettings.with(ResApplication.getContext()).GetDefaultPrice();
        String label="Price_1";
        if(index>0)
        {
            label= "Price_"+String.valueOf(index);
        }

        return label;
    }

    public static String getVersion() {
        return BuildConfig.VERSION_CODE+"."+BuildConfig.VERSION_NAME;
    }
}
