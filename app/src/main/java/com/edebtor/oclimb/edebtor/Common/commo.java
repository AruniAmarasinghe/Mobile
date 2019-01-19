package com.edebtor.oclimb.edebtor.Common;

import com.edebtor.oclimb.edebtor.Remote.IMyAPI;
import com.edebtor.oclimb.edebtor.Remote.RetrofitClient;


public class commo {
    public static final String BASE_URL = "http://g5.creditlanka.com/main/API/";

    public static IMyAPI getAPI(){

        return RetrofitClient.getCliet(BASE_URL).create(IMyAPI.class);

    }
}
