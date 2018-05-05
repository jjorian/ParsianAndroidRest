package ir.parsianandroid.parsianandroidres.Models;

import android.content.Context;
import android.inputmethodservice.Keyboard;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ir.parsianandroid.parsianandroidres.db.AppDatabase;

/**
 * Created by JavAd on 2018/02/10.
 */

public class FactorRow {

    int ID;
    long ProductCode;
    double Count;
    double Price;
    String Comment;
    String Label;

    Context vContext;
    public FactorRow(long ProductCode,double Count,String Label,double price,String comment)
    {
        this.ProductCode=ProductCode;
        this.Count=Count;
        this.Label=Label;
        this.Price=price;
        this.Comment=comment;
    }
    public FactorRow(Context ctx)
    {
        vContext=ctx;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public long getProductCode() {
        return ProductCode;
    }

    public void setProductCode(long productCode) {
        ProductCode = productCode;
    }

    public double getCount() {
        return Count;
    }

    public void setCount(double count) {
        Count = count;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public static void refreshFactorRow(List<FactorRow> list, FactorRow row)
    {

        if(list.size()==0)
        {
            list.add(row);


        }
        else {
            boolean find=false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getProductCode() == row.getProductCode()) {
                    if (row.Count > 0) {
                        list.get(i).setCount(list.get(i).getCount() + row.getCount());

                    } else {
                        list.get(i).setCount(list.get(i).getCount() + row.getCount());

                        if (list.get(i).getCount() == 0) {

                            list.remove(i);


                        }
                    }
                    find=true;
                    break;
                }

            }
            if(!find)
            {
                list.add(row);


            }
        }

    }

    public static double getFactorSum(List<FactorRow> list) {

        double sum=0;
        for (int i = 0; i < list.size(); i++) {
                sum=sum+(list.get(i).getCount()*list.get(i).getPrice());

        }
        return sum;
    }
    public static String FactorRowsToJson(List<FactorRow> rows)
    {
        Type listType = new TypeToken<List<FactorRow>>() {}.getType();
        Gson gson = new Gson();
        String json = gson.toJson(rows, listType);

        return json;
    }
    public static FactorRow With(Context ctx)
    {
        return new FactorRow(ctx);
    }
    public  void RefreshLabels(List<FactorRow> rows)
    {
        for (int i = 0; i < rows.size(); i++) {
            try {
                rows.get(i).setLabel(AppDatabase.getInstance(vContext).tProductDao().GetProduct(rows.get(i).getProductCode()).getCName());
            }
            catch (Exception ee)
            {
                rows.get(i).setLabel("کالا پیدا نشد");
            }

            
        }
    }

}
