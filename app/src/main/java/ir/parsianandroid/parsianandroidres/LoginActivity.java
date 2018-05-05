package ir.parsianandroid.parsianandroidres;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import ir.parsianandroid.parsianandroidres.Global.Dialogs;
import ir.parsianandroid.parsianandroidres.Global.GlobalUtils;
import ir.parsianandroid.parsianandroidres.Global.MyToast;
import ir.parsianandroid.parsianandroidres.Models.User;
import ir.parsianandroid.parsianandroidres.Global.RetrofitInitialize;
import ir.parsianandroid.parsianandroidres.db.AppDatabase;
import ir.parsianandroid.parsianandroidres.db.entity.TCategory;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final int REQUEST_APP_SETTINGS = 168;
    Button btn_login;
    private EditText txt_username;
    private EditText txt_password;
    ImageView img_logo;
    private EditText txt_servername;
    private EditText txt_port;
    private View mProgressView;
    private View mLoginFormView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 22 && !GlobalUtils.hasPermissions(GlobalUtils.requiredPermissions)) {
            ActivityCompat.requestPermissions(LoginActivity.this, GlobalUtils.requiredPermissions, REQUEST_APP_SETTINGS);
            // goToSettings();
        }
        if(!AppSettings.with(LoginActivity.this).GetUserName().equals(""))
        {
            MyToast.makeText(LoginActivity.this, "ورود موفقیت آمیز", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }
        setContentView(R.layout.activity_login);
        // Set up the login form.
        txt_username =  findViewById(R.id.txt_username);
        img_logo=  findViewById(R.id.img_logo);
        img_logo.setColorFilter(getResources().getColor(R.color.white));
        txt_servername = (EditText) findViewById(R.id.txt_serveradress);
        txt_port = (EditText) findViewById(R.id.txt_serverport);
        txt_password = (EditText) findViewById(R.id.txt_password);
        btn_login = findViewById(R.id.btn_login);

        txt_username.setText(AppSettings.with(LoginActivity.this).GetTempUserName());
        btn_login.requestFocus();
        //if(txt_username.getText().length()>0)
          //  txt_password.requestFocus();
        //else
          //  txt_username.requestFocus();
        GetServerAddress();
       // txt_username.setText("javad");
     //   txt_password.setText("123456");
        btn_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckInput()) {
                    String servername = "";
                    servername += "http://" + txt_servername.getText();
                    if (txt_port.getText().length() > 0)
                        servername += ":" + txt_port.getText() + "/";
                    else
                        servername += "/";
                    AppSettings.with(LoginActivity.this).SetServerBaseUrl(servername);
                    AppSettings.with(LoginActivity.this).SetTempUserName(txt_username.getText().toString());

                    login(txt_username.getText().toString(), txt_password.getText().toString());
                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private boolean CheckInput() {
        if(txt_username.getText().length()==0)
        {
            MyToast.makeText(this, "نام کاربر خالی است", Toast.LENGTH_SHORT).show();
            txt_username.requestFocus();
            return false;

        }
        if(txt_password.getText().length()==0)
        {
            MyToast.makeText(this, "کلمه عبور خالی است", Toast.LENGTH_SHORT).show();
            txt_password.requestFocus();
            return false;

        }
        if(txt_servername.getText().length()==0)
        {
            MyToast.makeText(this, "آدرس سرور خالی است", Toast.LENGTH_SHORT).show();
            txt_servername.requestFocus();
            return false;

        }

        return true;
    }

    public void GetServerAddress()
    {
        String server=AppSettings.with(LoginActivity.this).GeServerBaseUrl();

        try {
            if(server.contains(":")) {
                String[] str = server.split(":");


                str[1]=str[1].replace("/", "");
                str[2]=str[2].replace("/", "");




                txt_servername.setText(str[1]);
                txt_port.setText(str[2]);
            }
            else
            {
                server=server.replace("http://", "");
                server=server.replace("/", "");
                txt_servername.setText(server);

            }

        }
        catch (Exception ee)
        {
            ee.printStackTrace();
        }
    }
    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
       /* if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }*/
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void login(String username,String Password) {

        Dialogs dlg = new Dialogs(LoginActivity.this);
        dlg.ShowWaitDialog("صبور باشید", "در حال برقراری ارتباط با سرور");
        Call<ResponseBody> call = RetrofitInitialize.With(LoginActivity.this).webServices.Login(username, Password, GlobalUtils.getDeviceId());
        Callback<ResponseBody> callback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dlg.StopWaitDialog();
                try {

                    if (response.code() == HttpsURLConnection.HTTP_OK) {
                        Type listType = new TypeToken<ArrayList<User>>() {
                        }.getType();
                        List<User> list = new Gson().fromJson(response.body().string(), listType);

                        AppSettings app = new AppSettings(LoginActivity.this);
                        app.SetServerBaseUrl("http://" + txt_servername.getText().toString() + ":" + txt_port.getText().toString() + "/");
                        User user = list.get(0);
                        app.SetID(user.getID());
                        app.SetFullName(user.getFullName());
                        app.SetUserName(user.getUserName());
                        app.SetHashCode(user.getHashCode());
                        int id = Integer.valueOf(response.headers().get("AdminID"));
                        app.SetAdminID(id);


                        try {
                            int df = Integer.valueOf(response.headers().get("DefaultPrice"));
                            app.SetDefaultPrice(df);
                        } catch (Exception e) {
                            app.SetDefaultPrice(0);
                        }

                        try {
                            int AskPrintLocal = Integer.valueOf(response.headers().get("AskPrintLocal"));
                            app.SetAskPrintLocal(AskPrintLocal);

                        } catch (Exception e) {
                            app.SetDefaultPrice(0);
                        }

                        try {
                            int DoubleTable = Integer.valueOf(response.headers().get("DoubleTable"));
                            app.SetDoubleTable(DoubleTable);
                        } catch (Exception e) {
                            app.SetDoubleTable(0);
                        }


                        MyToast.makeText(LoginActivity.this, "ورود موفقیت آمیز", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();


                    } else if (response.code() == HttpsURLConnection.HTTP_INTERNAL_ERROR) {
                        MyToast.makeText(LoginActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();

                    } else if (response.code() == HttpsURLConnection.HTTP_NOT_FOUND) {
                        MyToast.makeText(LoginActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                    MyToast.makeText(LoginActivity.this, "خطایی در حین انجام عملیات", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dlg.StopWaitDialog();

                MyToast.makeText(LoginActivity.this, "شما به سرور متصل نمی شوید", Toast.LENGTH_SHORT).show();

            }
        };
        call.enqueue(callback);

    }




}


