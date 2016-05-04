package io.clutchstud.nfems.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import io.clutchstud.nfems.R;
import io.clutchstud.nfems.models.ProtocolRealmObject;
import io.realm.Realm;
import io.realm.RealmResults;

public class ProtocolDetailActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_detail);

        int protoId = getIntent().getIntExtra("protoId", 0);
        RealmResults<ProtocolRealmObject> protocolRealmObjects =  Realm.getDefaultInstance()
                                                                                .where(ProtocolRealmObject.class)
                                                                                .equalTo("id", getIntent().getIntExtra("protoId", 0))
                                                                                .findAll();

        webView = (WebView) findViewById(R.id.protocolWebView);

        String summary = "<html><body>Placeholder <b>123</b> Places held.</body></html>";

        webView.loadData(protocolRealmObjects.get(0).getHtmlcontent(), "text/html", null);
        //Toast.makeText(this, ""+protocolRealmObjects.get(0).getHtmlcontent(), Toast.LENGTH_SHORT).show();
    }
}
