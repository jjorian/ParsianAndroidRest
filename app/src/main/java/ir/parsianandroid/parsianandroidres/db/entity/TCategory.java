package ir.parsianandroid.parsianandroidres.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Arrays;

/**
 * Created by JavAd on 2018/02/08.
 */
@Entity(tableName = "TCategory")
public class TCategory {
    @PrimaryKey
    private int Code;
    private String Title;
    private byte[] Avatar;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
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
        return "Category{" +
                "Code=" + Code +
                ", Title='" + Title + '\'' +
                ", Avatar=" + Arrays.toString(Avatar) +
                '}';
    }
}
