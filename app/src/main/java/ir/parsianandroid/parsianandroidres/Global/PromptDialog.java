package ir.parsianandroid.parsianandroidres.Global;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ir.parsianandroid.parsianandroidres.R;


/**
 * Created by JaVAd on 11/12/2015.
 */

public class PromptDialog {
    public interface onResultButtonClick {

        void onResult(int code);
    }
    private onResultButtonClick result;

    public final static int Type_YES_NO=101;
    public final static int Type_OK=102;

    public final static int Result_YES=10;
    public final static int Result_NO=11;
    public final static int Result_OK=12;
    public void SetonResultListenner(int type,String title,String message,Context caller,onResultButtonClick listener)
    {
        result=listener;

        final Dialog dialog = new Dialog(caller);


        dialog.setContentView(R.layout.dialog_prompt);

         TextView txt_message=  dialog.findViewById(R.id.txt_message);
        TextView txt_title=  dialog.findViewById(R.id.txt_title);
         txt_message.setText(message);
        txt_title.setText(title);

        Button btn_yes=  dialog.findViewById(R.id.btn_yes);
        Button btn_no=  dialog.findViewById(R.id.btn_no);
        Button btn_ok=  dialog.findViewById(R.id.btn_ok);
        if(type==Type_OK)
        {
            btn_no.setVisibility(View.INVISIBLE);
            btn_yes.setVisibility(View.INVISIBLE);
        }
        else if(type==Type_YES_NO)
        {
            btn_ok.setVisibility(View.INVISIBLE);
        }
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                result.onResult(Result_YES);

            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                result.onResult(Result_NO);


            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                result.onResult(Result_OK);
            }
        });



        dialog.show();


    }





}
