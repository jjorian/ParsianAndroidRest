package ir.parsianandroid.parsianandroidres.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Arrays;

/**
 * Created by JavAd on 2018/02/09.
 */
@Entity
public class TCategoryKey {
    @PrimaryKey
    private    int ID ;
    private int CategoryCode;
    private long GoodCode ;
    private String Title ;
    private byte[] Avatar ;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCategoryCode() {
        return CategoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        CategoryCode = categoryCode;
    }

    public long getGoodCode() {
        return GoodCode;
    }

    public void setGoodCode(long goodCode) {
        GoodCode = goodCode;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public byte[] getAvatar() {
        return Avatar;
    }

    public void setAvatar(byte[] avatar) {
        Avatar = avatar;
    }

    @Override
    public String toString() {
        return "TCategoryKey{" +
                "ID=" + ID +
                ", CategoryCode=" + CategoryCode +
                ", GoodCode=" + GoodCode +
                ", Title='" + Title + '\'' +
                ", Avatar=" + Arrays.toString(Avatar) +
                '}';
    }
}
