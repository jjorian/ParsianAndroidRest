package ir.parsianandroid.parsianandroidres.Interface;


import java.util.List;

import ir.parsianandroid.parsianandroidres.Models.User;
import ir.parsianandroid.parsianandroidres.db.entity.TCategory;
import ir.parsianandroid.parsianandroidres.db.entity.TTables;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by JavAd on 2018/02/06.
 */

public interface APIService {

    @GET("api/hesab/testAdmin")
    Call<ResponseBody> testAdmin();

    @GET("api/user/getUsers")
    Call<List<User>> getUsers();

    @GET("api/user/login/{UserName}/{Password}/{Serial}")
    Call<ResponseBody> Login(@Path("UserName") String username, @Path("Password") String password, @Path("Serial") String serial);

    @GET("api/Update/FetchData/{Code}")
    Call<ResponseBody> FetchData(@Path("Code") int Code);

    @POST("api/Update/SaveFactor")
    Call<ResponseBody> SaveFactor(@Body String value);

    @GET("api/Update/TableStatus/{Code}")
    Call<List<TTables>> TableStatus(@Path("Code") int Code);

    @GET("api/Update/GetFactor/{fNum}/{tNum}")
    Call<ResponseBody> GatFactor(@Path("fNum") int fNum,@Path("tNum") int tNum);

    @GET("api/Hesab/CheckSoftwareVersion/{Serial}/{Version}")
    Call<ResponseBody> CheckSoftwareVersion(@Path("Serial") String Serial,@Path("Version") String Version);

    @POST("api/Hesab/SendOpinion")
    Call<ResponseBody> SendOpinion(@Body String value);




}
