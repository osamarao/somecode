package io.clutchstud.nfems.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import io.clutchstud.nfems.R;
import io.clutchstud.nfems.services.SyncProtocolsService;
import io.clutchstud.nfems.util.AlarmHandlingUtility;

public class SettingsActivity extends AppCompatActivity {

    private Button btnSaveSettings;
    private RadioGroup radioGroup;
    private RadioButton rdoFiveHours,rdoTwelveHours, rdoTwentyFourHours;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnSaveSettings = (Button) findViewById(R.id.btnSaveSettings);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rdoFiveHours = (RadioButton) findViewById(R.id.rdoFiveHours);
        rdoTwelveHours = (RadioButton) findViewById(R.id.rdoTwelveHours);
        rdoTwentyFourHours = (RadioButton) findViewById(R.id.rdoTwentyFourHours);


        prefs = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

        // Set alarm to current Settings
        switch(prefs.getInt("syncInterValInHours", 24)){
            case 5:
                rdoFiveHours.setSelected(true);
                break;
            case 12:
                rdoTwelveHours.setSelected(true);
                break;
            case 24:
                rdoTwentyFourHours.setSelected(true);
                break;


        }


        btnSaveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdoFiveHours.isSelected()){
                    prefs.edit().putInt("syncInterValInHours", 5).apply();
                    AlarmHandlingUtility.setAlarm(SettingsActivity.this, prefs.getInt("syncInterValInHours", 24), new Intent(SettingsActivity.this, SyncProtocolsService.class));
                }

                if (rdoTwelveHours.isSelected()){
                    prefs.edit().putInt("syncInterValInHours", 12).apply();
                    AlarmHandlingUtility.setAlarm(SettingsActivity.this, prefs.getInt("syncInterValInHours", 24), new Intent(SettingsActivity.this, SyncProtocolsService.class));
                }

                if (rdoTwentyFourHours.isSelected()){
                    prefs.edit().putInt("syncInterValInHours", 24).apply();
                    AlarmHandlingUtility.setAlarm(SettingsActivity.this, prefs.getInt("syncInterValInHours", 24), new Intent(SettingsActivity.this, SyncProtocolsService.class));
                }
            }
        });


    }
}
