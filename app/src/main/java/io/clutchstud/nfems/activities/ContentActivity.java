package io.clutchstud.nfems.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.clutchstud.nfems.R;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("catId")) {
            Snackbar.make(this.findViewById(R.id.toolbar), "catId is : " + getIntent().getIntExtra("catId", 0), Snackbar.LENGTH_SHORT).show();
        }


    }

}
