package ir.parsianandroid.parsianandroidres.Models;
/**
 * Created by JavAd on 2018/02/06.
 */
public class User{
     int ID ;
     String FullName ;
     String UserName ;
     String Password ;
     int Status ;
     String HashCode ;
    String Serial ;

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", FullName='" + FullName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", Status=" + Status +
                ", HashCode='" + HashCode + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getHashCode() {
        return HashCode;
    }

    public void setHashCode(String hashCode) {
        HashCode = hashCode;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String serial) {
        Serial = serial;
    }
}

