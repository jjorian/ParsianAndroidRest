package ir.parsianandroid.parsianandroidres.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Arrays;

/**
 * Created by JavAd on 2018/02/08.
 */
@Entity(tableName = "TTables")
public class TTables {
    @PrimaryKey
    private int Code;
    private String Salon;
    private String Title;
    private int Status;
    private String ReserveTime;
    private String ReserveDes;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getSalon() {
        return Salon;
    }

    public void setSalon(String salon) {
        Salon = salon;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getReserveTime() {
        return ReserveTime;
    }

    public void setReserveTime(String reserveTime) {
        ReserveTime = reserveTime;
    }

    public String getReserveDes() {
        return ReserveDes;
    }

    public void setReserveDes(String reserveDes) {
        ReserveDes = reserveDes;
    }

    @Override
    public String toString() {
        return "TTables{" +
                "Code=" + Code +
                ", Salon='" + Salon + '\'' +
                ", Title='" + Title + '\'' +
                ", Status=" + Status +
                ", ReserveTime='" + ReserveTime + '\'' +
                ", ReserveDes='" + ReserveDes + '\'' +
                '}';
    }
}
