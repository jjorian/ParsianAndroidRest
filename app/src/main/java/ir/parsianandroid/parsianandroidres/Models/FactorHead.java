package ir.parsianandroid.parsianandroidres.Models;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by JavAd on 2018/02/10.
 */

public class FactorHead {
    int ID;
    int FactorNumber;
    int FactorKind;
    String Customer;
    int TableNumber;
    String Des;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getFactorNumber() {
        return FactorNumber;
    }

    public void setFactorNumber(int factorNumber) {
        FactorNumber = factorNumber;
    }

    public int getFactorKind() {
        return FactorKind;
    }

    public void setFactorKind(int factorKind) {
        FactorKind = factorKind;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public int getTableNumber() {
        return TableNumber;
    }

    public void setTableNumber(int tableNumber) {
        TableNumber = tableNumber;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }
    public void Initialize()
    {
        ID=0;
        FactorNumber=0;
        FactorKind=0;
        Customer="";
        TableNumber=0;
        Des="";

    }
    public static String FactorHeadToJson(FactorHead head)
    {
        Type listType = new TypeToken<FactorHead>() {}.getType();
        Gson gson = new Gson();
        String json = gson.toJson(head, listType);
        return json;


    }
}
