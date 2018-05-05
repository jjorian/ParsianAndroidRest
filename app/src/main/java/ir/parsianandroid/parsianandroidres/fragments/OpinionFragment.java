package ir.parsianandroid.parsianandroidres.fragments;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import ir.parsianandroid.parsianandroidres.AppSettings;
import ir.parsianandroid.parsianandroidres.BuildConfig;
import ir.parsianandroid.parsianandroidres.Global.Constants;
import ir.parsianandroid.parsianandroidres.Global.GlobalUtils;
import ir.parsianandroid.parsianandroidres.Global.MyToast;
import ir.parsianandroid.parsianandroidres.Global.RetrofitInitialize;
import ir.parsianandroid.parsianandroidres.Models.FactorHead;
import ir.parsianandroid.parsianandroidres.Models.FactorRow;
import ir.parsianandroid.parsianandroidres.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OpinionFragment extends DialogFragment {
    public static final int Code_Refresh_chat = 1002;
    private String Title;

    Button btn_send;
    TextView txt_des;
    TextView txt_title;
    EditText txt_text;
    ProgressDialog pDialog;
    AppSettings appSetting;

    public OpinionFragment()
    {


    }
    @SuppressLint("ValidFragment")
    public OpinionFragment(String title)
    {
        Title=title;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_opinion, container, false);

        appSetting = new AppSettings(getActivity());
        txt_text = (EditText) rootView.findViewById(R.id.opi_txt_text);
        btn_send = (Button) rootView.findViewById(R.id.opi_btn_send);
        txt_des= (TextView) rootView.findViewById(R.id.opi_txt_des);
        txt_title= (TextView) rootView.findViewById(R.id.txt_title);
        txt_title.setText(Title);
        btn_send.setOnClickListener(view -> {
            if( txt_text.getText().toString().length()>10) {
                JSONObject obj=new JSONObject();
                try {
                    obj.put("SenderID", appSetting.GetAdminID());
                    obj.put("SenderType", "L");
                    obj.put("Text",txt_text.getText().toString());
                    obj.put("DateSend", "");
                    obj.put("Answer", appSetting.GetUserName()+"-"+appSetting.GeFullName());

                } catch (Exception ee) {

                }
                Call<ResponseBody> call = RetrofitInitialize.With(getActivity(), Constants.BaseServerAddress).webServices.SendOpinion(GlobalUtils.ReadyPost(obj));
                Callback<ResponseBody> callback = new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {


                                JSONObject obj=new JSONObject(response.body().string());

                                MyToast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();


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
                call.enqueue(callback);            }
            else
            {
                MyToast.makeText(getActivity(), getString(R.string.msg_notemptyyourtext), Toast.LENGTH_SHORT).show();
            }
        });
        //txt_text.setTypeface(GlobalSetting.SetFonts(GlobalSetting.FontNumbers, getActivity()));
        //btn_send.setTypeface(GlobalSetting.SetFonts(GlobalSetting.FontTitels, getActivity()));
        txt_text.setTextSize(22);
        txt_des.setText(getFromAssest());

        return rootView;
    }


    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    public String getFromAssest() {
        try {
            InputStream is = getActivity().getAssets().open("Opiniontext.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer);
            return text;
        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }

    }


}
