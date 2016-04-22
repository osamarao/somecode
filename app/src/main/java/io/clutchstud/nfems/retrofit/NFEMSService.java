package io.clutchstud.nfems.retrofit;

import java.util.ArrayList;

import io.clutchstud.nfems.models.Category;
import io.clutchstud.nfems.models.Protocol;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by a653h496 on 4/4/16.
 */
public interface NFEMSService {

    @GET("categories")
    Call<ArrayList<Category>> listCategories();

    @GET("/protocols")
    Call<ArrayList<Protocol>> listProtocols();
}
