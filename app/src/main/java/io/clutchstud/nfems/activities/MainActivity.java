package io.clutchstud.nfems.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;

import io.clutchstud.nfems.R;
import io.clutchstud.nfems.models.ProtocolRealmObject;
import io.clutchstud.nfems.services.SyncProtocolsService;
import io.clutchstud.nfems.util.MenuPopulator;
import io.clutchstud.nfems.util.NavigationSubMenuPopulator;
import io.clutchstud.nfems.util.ProtocolTitleMenuItem;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RealmConfiguration realmConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EventBus.getDefault().register(this);
        gainPermission();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);


        Menu m = navigationView.getMenu();
        SubMenu topChannelMenu = m.addSubMenu("Top Channels");

        MenuPopulator menuPopulator = new NavigationSubMenuPopulator(topChannelMenu);

        menuPopulator.add(new ProtocolTitleMenuItem(Menu.NONE, 1, Menu.NONE, "Foo"));
        menuPopulator.add(new ProtocolTitleMenuItem(Menu.NONE, 2, Menu.NONE, "Bar"));
        menuPopulator.add(new ProtocolTitleMenuItem(Menu.NONE, 3, Menu.NONE, "Baz"));
        menuPopulator.add(new ProtocolTitleMenuItem(Menu.NONE, 4, Menu.NONE, "Paz"));


    }

    private void gainPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 90);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

            realmConfiguration = new RealmConfiguration.Builder(this).build();
        }
    }


    @Subscribe
    public void handleSyncDoneEvent(SyncProtocolsService.SyncDoneEvent event){
        // We can now update the navigation
        Snackbar.make(this.findViewById(R.id.toolbar), "Sync Complete", Snackbar.LENGTH_SHORT).show();
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(this).build());
        RealmResults<ProtocolRealmObject> protocols = Realm.getDefaultInstance().where(ProtocolRealmObject.class).findAll();
        Log.d("handleSomething", Arrays.deepToString(protocols.toArray()));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Snackbar.make(this.findViewById(R.id.toolbar), "Feature not available", Snackbar.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.action_sync_now) {
//            Snackbar.make(this.findViewById(R.id.toolbar), "Feature not available", Snackbar.LENGTH_SHORT).show();
            Intent msgIntent = new Intent(MainActivity.this, SyncProtocolsService.class);
            startService(msgIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
