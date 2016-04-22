package io.clutchstud.nfems.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import io.clutchstud.nfems.models.Category;
import io.clutchstud.nfems.models.CategoryRealmObject;
import io.clutchstud.nfems.models.Protocol;
import io.clutchstud.nfems.models.ProtocolRealmObject;
import io.clutchstud.nfems.retrofit.NFEMSService;
import io.clutchstud.nfems.retrofit.NFEMSServiceFactory;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by a653h496 on 4/21/16.
 */
public class SyncProtocolsService extends IntentService {

    private final NFEMSService nfemsService;
    private final RealmConfiguration realmConfiguration;
    private final Realm realm;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SyncProtocolsService(String name) {
        super(name);
        nfemsService = NFEMSServiceFactory.getNFEMSService();
        realmConfiguration = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(realmConfiguration);
    }

    @Override
    protected void onHandleIntent(Intent intent) {



        getProtocols();
        getCategories();


    }

    private void getCategories() {
        Call<ArrayList<Category>> categoryListCall = nfemsService.listCategories();
        categoryListCall.clone().enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                ArrayList<CategoryRealmObject> realmObjects = new ArrayList<CategoryRealmObject>(response.body().size());

                for (Category category : response.body()) {
                    realmObjects.add(new CategoryRealmObject(category));
                }

                realm.beginTransaction();
                realm.copyToRealm(realmObjects);
                realm.commitTransaction();


            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Log.e(this.getClass().getCanonicalName() + " getCategories Failure", t.getMessage());
            }
        });
    }

    private void getProtocols() {
        Call<ArrayList<Protocol>> protocolListCall = nfemsService.listProtocols();
        protocolListCall.clone().enqueue(new Callback<ArrayList<Protocol>>() {
            @Override
            public void onResponse(Call<ArrayList<Protocol>> call, Response<ArrayList<Protocol>> response) {
                ArrayList<ProtocolRealmObject> realmObjects = new ArrayList<ProtocolRealmObject>(response.body().size());

                for (Protocol category : response.body()) {
                    realmObjects.add(new ProtocolRealmObject(category));
                }

                realm.beginTransaction();
                realm.copyToRealm(realmObjects);
                realm.commitTransaction();


            }

            @Override
            public void onFailure(Call<ArrayList<Protocol>> call, Throwable t) {
                Log.e(this.getClass().getCanonicalName() + " getProtocols Failure", t.getMessage());
            }
        });
    }
}
