package ir.parsianandroid.parsianandroidres.fragments;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import ir.parsianandroid.parsianandroidres.AppSettings;
import ir.parsianandroid.parsianandroidres.BuildConfig;
import ir.parsianandroid.parsianandroidres.Global.Constants;
import ir.parsianandroid.parsianandroidres.Global.GlobalUtils;
import ir.parsianandroid.parsianandroidres.Global.MyToast;
import ir.parsianandroid.parsianandroidres.Global.PromptDialog;
import ir.parsianandroid.parsianandroidres.Global.RetrofitInitialize;
import ir.parsianandroid.parsianandroidres.MainActivity;
import ir.parsianandroid.parsianandroidres.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutMeDialogFragment  extends DialogFragment {
    public static final int Code_Refresh_chat = 1002;
    private String Title;
    Button btn_version;
    Button btn_downloadcenter;
    Button btn_lastchange;
    Button btn_manual;
    TextView txt_version;
    TextView txt_text;
    TextView txt_call;
    TextView txt_mversion;
    TextView txt_mobilecall;
    TextView txt_email;
    TextView txt_title;

    ImageView img_logo;
    TextView txt_telegram;
    TextView txt_web;
    ProgressDialog pDialog;
    AppSettings appSetting;
    TextView txt_versiondate;

    public AboutMeDialogFragment()
    {


    }
    @SuppressLint("ValidFragment")
    public AboutMeDialogFragment(String title)
    {
        Title=title;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_aboutme, container, false);


        appSetting = new AppSettings(getActivity());
        txt_versiondate= (TextView) rootView.findViewById(R.id.about_txt_versiondate);
        txt_version = (TextView) rootView.findViewById(R.id.about_txt_version);
        txt_text = (TextView) rootView.findViewById(R.id.about_txt_text);
        img_logo = (ImageView) rootView.findViewById(R.id.about_img_logo);


        btn_version = (Button) rootView.findViewById(R.id.about_btn_check);
        btn_downloadcenter = (Button) rootView.findViewById(R.id.about_btn_downloadcenter);
        btn_lastchange = (Button) rootView.findViewById(R.id.about_btn_lastchange);
        btn_manual = (Button) rootView.findViewById(R.id.about_btn_manual);

        txt_mversion=rootView.findViewById(R.id.txt_mversion);
        txt_call = (TextView) rootView.findViewById(R.id.about_txt_call);
        txt_mobilecall = (TextView) rootView.findViewById(R.id.about_txt_mobilecall);
        txt_telegram = (TextView) rootView.findViewById(R.id.about_txt_telegram);
        txt_title = (TextView) rootView.findViewById(R.id.txt_title);
        txt_email = (TextView) rootView.findViewById(R.id.about_txt_email);
        txt_web = (TextView) rootView.findViewById(R.id.about_txt_site);
        //txt_text.setText(getFromAssest());

        //txt_text.setTypeface(GlobalSetting.SetFonts(GlobalSetting.FontNumbers, getActivity()));
        txt_versiondate.setText(getString(R.string.txt_publishdateversion));
        img_logo.setColorFilter(getResources().getColor(R.color.primary_dark));
        txt_title.setText(Title);
        txt_email.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub


                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("plain/text");
                i.putExtra(Intent.EXTRA_EMAIL, txt_email.getText());
                i.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.app_name));

                i.putExtra(Intent.EXTRA_TEXT, "------- " + appSetting.GeFullName());
                startActivity(Intent.createChooser(i, getString(R.string.txt_sendemail)));


            }
        });
        btn_version.setOnClickListener(v -> {
            // TODO Auto-generated method stub

            Call<ResponseBody> call = RetrofitInitialize.With(getActivity(),Constants.BaseServerAddress).webServices.CheckSoftwareVersion(GlobalUtils.serialCreator(), GlobalUtils.getVersion().replace('.','-'));
            Callback<ResponseBody> callback = new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {


                            MyToast.makeText(getActivity(), response.body().string(), Toast.LENGTH_SHORT).show();


                        } catch (Exception ee) {
                            ee.printStackTrace();
                            MyToast.makeText(getActivity(), "خطایی در حین پردازش اطلاعات", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        MyToast.makeText(getActivity(), "خطایی در حین انجام عملیات", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    MyToast.makeText(getActivity(), "شما به سرور متصل نمی شوید", Toast.LENGTH_SHORT).show();

                }
            };
            call.enqueue(callback);

        });
        btn_lastchange.setOnClickListener(v -> {
            // TODO Auto-generated method stub


            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.BaseServerAddress+ "Pages/LastChanges/"+GlobalUtils.serialCreator()+"/" +GlobalUtils.getVersion().replace('.','-')));
            startActivity(i);
        });
        btn_manual.setOnClickListener(v -> {
            // TODO Auto-generated method stub

            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.BaseServerAddress+ "Pages/Manual/"+GlobalUtils.serialCreator()+"/" + GlobalUtils.getVersion().replace('.','-')));
            startActivity(i);
        });
        txt_call.setOnClickListener(arg0 -> {
            // TODO Auto-generated method stub
            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + txt_call.getText()));
            startActivity(i);

        });
        btn_downloadcenter.setOnClickListener(view -> {

            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.BaseServerAddress+ "Pages/DownLoadCenter/"+GlobalUtils.serialCreator()+"/"+ GlobalUtils.getVersion().replace('.','-')));
            startActivity(i);

        });
        txt_mobilecall.setOnClickListener(arg0 -> {
            // TODO Auto-generated method stub
            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + txt_mobilecall.getText()));
            startActivity(i);

        });

        txt_web.setOnClickListener(arg0 -> {
            // TODO Auto-generated method stub
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(txt_web.getText() + ""));
            startActivity(i);

        });
        txt_telegram.setOnClickListener(arg0 -> {
            // TODO Auto-generated method stub
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/parsianandroid"));
            startActivity(i);

        });
        txt_mversion.setText("نسخه "+GlobalUtils.getVersion());

        return rootView;
    }


    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }






}
