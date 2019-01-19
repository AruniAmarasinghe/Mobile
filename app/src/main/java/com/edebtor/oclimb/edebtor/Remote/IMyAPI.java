package com.edebtor.oclimb.edebtor.Remote;

import com.edebtor.oclimb.edebtor.Model.APIRespose;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
//
public interface IMyAPI {

    @FormUrlEncoded
    @POST("login.php")
    Call<APIRespose> loginUser(@Field("uname") String uname, @Field("pass") String pass );


}
