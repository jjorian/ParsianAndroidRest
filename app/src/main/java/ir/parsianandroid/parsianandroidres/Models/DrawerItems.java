package ir.parsianandroid.parsianandroidres.Models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Behzad on 28/10/2017.
 */

public class DrawerItems {
    public int ID;
    public String Title;
    public int IconID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getIconID() {
        return IconID;
    }

    public void setIconID(int iconID) {
        IconID = iconID;
    }

    public  static List<DrawerItems> getItems(Context ctx)
    {
        List<DrawerItems> list=new ArrayList<>();
        DrawerItems l1;
        l1=new DrawerItems();
        l1.setID(101);
        l1.setTitle("صفحه اصلی");
        l1.setIconID(ctx.getResources().getIdentifier("svg_home","drawable",ctx.getPackageName()));
        list.add(l1);


        l1=new DrawerItems();
        l1.setID(104);
        l1.setTitle("درباره ما");
        l1.setIconID(ctx.getResources().getIdentifier("svg_aboutme","drawable",ctx.getPackageName()));
        list.add(l1);


        l1=new DrawerItems();
        l1.setID(102);
        l1.setTitle("نظرات و پیشنهادات");
        l1.setIconID(ctx.getResources().getIdentifier("svg_chat","drawable",ctx.getPackageName()));
        list.add(l1);




        /*l1=new DrawerItems();
        l1.setID(103);
        l1.setTitle("تغییر کلمه عبور");
        l1.setIconID(ctx.getResources().getIdentifier("svg_checkversion","drawable",ctx.getPackageName()));
        list.add(l1);*/

        l1=new DrawerItems();
        l1.setID(105);
        l1.setTitle("ورود مجدد");
        l1.setIconID(ctx.getResources().getIdentifier("svg_logout","drawable",ctx.getPackageName()));
        list.add(l1);


        l1=new DrawerItems();
        l1.setID(106);
        l1.setTitle("خروج");
        l1.setIconID(ctx.getResources().getIdentifier("svg_exit","drawable",ctx.getPackageName()));
        list.add(l1);
        return  list;



    }


}
