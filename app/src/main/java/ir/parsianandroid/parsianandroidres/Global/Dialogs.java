package ir.parsianandroid.parsianandroidres.Global;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by JavAd on 2018/02/28.
 */

public  class Dialogs {
    public  ProgressDialog progressDoalog;
    Context vContext;
    public Dialogs(Context caller)
    {
        vContext=caller;
    }
    public  void ShowWaitDialog(String title, String message)
    {
        progressDoalog = new ProgressDialog(vContext);
        progressDoalog.setTitle(title);
        progressDoalog.setMessage(message);
        progressDoalog.setIndeterminate(false);
        progressDoalog.setCanceledOnTouchOutside(false);
        progressDoalog.setCancelable(true);
        progressDoalog.show();
    }
    public  void StopWaitDialog() {
        progressDoalog.dismiss();
    }


}
