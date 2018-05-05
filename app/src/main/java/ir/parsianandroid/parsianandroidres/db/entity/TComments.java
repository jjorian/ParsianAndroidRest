package ir.parsianandroid.parsianandroidres.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by JavAd on 2018/02/06.
 */
@Entity
public class TComments {
    @PrimaryKey
     int ID ;
     String Comment ;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}

