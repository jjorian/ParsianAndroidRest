package ir.parsianandroid.parsianandroidres.Models;

/**
 * Created by JavAd on 2018/02/09.
 */

public class ProductCategory {
        private long Code;
        private String CName;
        private String Title;
        private double Price_1;
    private double Price_2;
    private double Price_3;
    private double Price_4;
    private double Price_5;

    public long getCode() {
        return Code;
    }

    public void setCode(long code) {
        Code = code;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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
    public ProductCategory With()
    {
        return new ProductCategory();
    }
    public double getPrice(int index)
    {
        switch (index)
        {
            case 1:
                return Price_1;
            case 2:
                return Price_2;
            case 3:
                return Price_3;
            case 4:
                return Price_4;
            case 5:
                return Price_5;
            default:
                return Price_1;
        }
    }
}
