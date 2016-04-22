package io.clutchstud.nfems.retrofit;

import com.squareup.moshi.Moshi;

import io.clutchstud.nfems.models.CategoryAdapter;
import io.clutchstud.nfems.models.ProtocolAdapter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by a653h496 on 4/4/16.
 */
public class NFEMSServiceFactory  {

    public static NFEMSService getNFEMSService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Moshi moshi = new Moshi.Builder().build();

        moshi = moshi
                .newBuilder()
                .add(new CategoryAdapter(moshi))
                .add(new ProtocolAdapter(moshi))
                .build();

        //Type listMyData = Types.newParameterizedType(List.class, Category.class);
        //JsonAdapter<ArrayList<Category>> arrayListJsonAdapter = moshi.adapter(listMyData);

      //  moshi = moshi.newBuilder().add(listMyData, arrayListJsonAdapter).build();


//add(listMyData, arrayListJsonAdapter).


        //2016-04-14T20:35:36.724Z


                Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(NFEMSServiceConstants.STAGING_API_BASE_URL)
                            .addConverterFactory(MoshiConverterFactory.create(moshi))
                            .client(client)
                            .build();


        return retrofit.create(NFEMSService.class);
    }
}
