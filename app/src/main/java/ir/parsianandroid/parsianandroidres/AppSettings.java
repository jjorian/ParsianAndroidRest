package ir.parsianandroid.parsianandroidres;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

/**
 * Created by JavAd on 2018/02/07.
 */

public class AppSettings extends Preference {
    private static AppSettings Instance;
    private SharedPreferences preferences;
    private Context aContext;


    public AppSettings(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        aContext = context;
    }

    public static AppSettings with(Context context) {

        if (Instance == null) {
            Instance = new AppSettings(context);
        }
        return Instance;
    }

    public String GeServerBaseUrl() {
        return preferences.getString("ServerBaseUrl", "");
    }

    public void SetServerBaseUrl(String Value) {
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putString("ServerBaseUrl", Value);
        prefEditor.apply();
    }

    public String GetUserName() {
        return preferences.getString("UserName", "");
    }

    public void SetUserName(String Value) {
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putString("UserName", Value);
        prefEditor.apply();
    }
    public String GetTempUserName() {
        return preferences.getString("TempUserName", "");
    }

    public void SetTempUserName(String Value) {
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putString("TempUserName", Value);
        prefEditor.apply();
    }


    public String GeFullName() {
        return preferences.getString("FullName", "");
    }

    public void SetFullName(String Value) {
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putString("FullName", Value);
        prefEditor.apply();
    }


    public String GeHashCode() {
        return preferences.getString("HashCode", "");
    }

    public void SetHashCode(String Value) {
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putString("HashCode", Value);
        prefEditor.apply();
    }

    public String GetLastTableStatus() {
        return preferences.getString("LastTableStatus", "آخرین وضعیت دریافت نشده");
    }

    public void SetLastTableStatus(String Value) {
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putString("LastTableStatus", Value);
        prefEditor.apply();
    }


    public int GeID() {
        return preferences.getInt("ID", 0);
    }

    public void SetID(int Value) {
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putInt("ID", Value);
        prefEditor.apply();
    }


    public int GetAdminID() {
        return preferences.getInt("AdminID", 0);
    }

    public void SetAdminID(int Value) {
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putInt("AdminID", Value);
        prefEditor.apply();
    }


    public void LogOutUser() {
        SharedPreferences.Editor prefEditor = preferences.edit();

        prefEditor.putString("UserName", "");
        prefEditor.putString("FullName", "");
        prefEditor.putString("HashCode", "");
        prefEditor.putString("LastTableStatus", "");
        prefEditor.putInt("ID", 0);


        prefEditor.apply();
    }

    public int GetDefaultPrice() {
        return preferences.getInt("DefaultPrice", 0);
    }

    public void SetDefaultPrice(int Value) {
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putInt("DefaultPrice", Value);
        prefEditor.apply();
    }

    public int GetAskPrintLocal() {
        return preferences.getInt("AskPrintLocal", 0);
    }

    public void SetAskPrintLocal(int Value) {
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putInt("AskPrintLocal", Value);
        prefEditor.apply();
    }

    public int GetDoubleTable() {
        return preferences.getInt("DoubleTable", 0);
    }

    public void SetDoubleTable(int Value) {
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putInt("DoubleTable", Value);
        prefEditor.apply();
    }

}
