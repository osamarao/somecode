package io.clutchstud.nfems.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import io.clutchstud.nfems.R;
import io.clutchstud.nfems.adapters.ProtocolListItemAdapter;
import io.clutchstud.nfems.models.ProtocolRealmObject;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ContentActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.protocolList);


        if (getIntent().hasExtra("catId")) {
            Realm.setDefaultConfiguration(new RealmConfiguration.Builder(this).build());
            RealmResults<ProtocolRealmObject> protocols = Realm.getDefaultInstance()
                    .where(ProtocolRealmObject.class)
                    .equalTo("categoryId", getIntent().getIntExtra("catId", 0))
                    .findAll();
            final ProtocolListItemAdapter protocolAdapter = new ProtocolListItemAdapter(protocols);


            recyclerView.setAdapter(protocolAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            protocolAdapter.setListener(new ProtocolListItemAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ProtocolRealmObject protocolRealmObject) {
                    Log.d("protocol: ", protocolRealmObject.toString());
                    Intent protocolDetailIntent = new Intent(ContentActivity.this, ProtocolDetailActivity.class);
                    protocolDetailIntent.putExtra("protoId", protocolRealmObject.getId());
                    startActivity(protocolDetailIntent);
                }
            });

        }


    }

}
