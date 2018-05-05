package ir.parsianandroid.parsianandroidres.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by JavAd on 2018/02/09.
 */
@Entity(tableName = "TProducts")
public class TProducts {
    @PrimaryKey
    private long Code;
    private String Barcode;
    private String UnitName_1;
    private String CName;
    private double Equal;
    private double Price_1;
    private double Price_2;
    private double Price_3;
    private double Price_4;
    private double Price_5;
    private String Other;

    public long getCode() {
        return Code;
    }

    public void setCode(long code) {
        Code = code;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getUnitName_1() {
        return UnitName_1;
    }

    public void setUnitName_1(String unitName_1) {
        UnitName_1 = unitName_1;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public double getEqual() {
        return Equal;
    }

    public void setEqual(double equal) {
        Equal = equal;
    }

    public double getPrice_1() {
        return Price_1;
    }

    public void setPrice_1(double price_1) {
        Price_1 = price_1;
    }

    public double getPrice_2() {
        return Price_2;
    }

    public void setPrice_2(double price_2) {
        Price_2 = price_2;
    }

    public double getPrice_3() {
        return Price_3;
    }

    public void setPrice_3(double price_3) {
        Price_3 = price_3;
    }

    public double getPrice_4() {
        return Price_4;
    }

    public void setPrice_4(double price_4) {
        Price_4 = price_4;
    }

    public double getPrice_5() {
        return Price_5;
    }

    public void setPrice_5(double price_5) {
        Price_5 = price_5;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }
}
