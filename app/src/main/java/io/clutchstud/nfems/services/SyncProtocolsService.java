package io.clutchstud.nfems.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import io.clutchstud.nfems.R;
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

    private NFEMSService nfemsService;
    private RealmConfiguration realmConfiguration;
    private Realm realm;
    private NotificationManager mNotificationManager;


    public SyncProtocolsService() {
        super("SimpleIntentService");

    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SyncProtocolsService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO Ongoing Notification to show stuffs happening
        nfemsService = NFEMSServiceFactory.getNFEMSService();
        startNotification();
        getCategories();
    }

    private void startNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("NFEMS")
                        .setOngoing(true)
                        .setContentText("Syncing...");


        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(890, mBuilder.build());
    }

    private void getCategories() {
        Call<ArrayList<Category>> categoryListCall = nfemsService.listCategories();
        categoryListCall.clone().enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                ArrayList<CategoryRealmObject> realmObjects = new ArrayList<CategoryRealmObject>(response.body().size());
                realmConfiguration = new RealmConfiguration.Builder(SyncProtocolsService.this).build();
                realm = Realm.getInstance(realmConfiguration);
                for (Category category : response.body()) {
                    realmObjects.add(new CategoryRealmObject(category));
                }

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(realmObjects);
                realm.commitTransaction();

                getProtocols();
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Log.e(this.getClass().getCanonicalName() + " getCategories Failure", ""+t.getMessage());
            }
        });
    }

    private void getProtocols() {
        Call<ArrayList<Protocol>> protocolListCall = nfemsService.listProtocols();
        protocolListCall.clone().enqueue(new Callback<ArrayList<Protocol>>() {
            @Override
            public void onResponse(Call<ArrayList<Protocol>> call, Response<ArrayList<Protocol>> response) {
                ArrayList<ProtocolRealmObject> realmObjects = new ArrayList<ProtocolRealmObject>(response.body().size());
                realmConfiguration = new RealmConfiguration.Builder(SyncProtocolsService.this).build();
                realm = Realm.getInstance(realmConfiguration);
                for (Protocol category : response.body()) {
                    realmObjects.add(new ProtocolRealmObject(category));
                }

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(realmObjects);
                realm.commitTransaction();

                // use Event Bus here
                EventBus.getDefault().post(new SyncDoneEvent("Hello everyone!"));
                mNotificationManager.cancel(890);
            }

            @Override
            public void onFailure(Call<ArrayList<Protocol>> call, Throwable t) {
                Log.e(this.getClass().getCanonicalName() + " getProtocols Failure", t.getMessage());
            }
        });
    }

    public class SyncDoneEvent {
        public final String message;

        public SyncDoneEvent(String message) {
            this.message = message;
        }
    }
}
