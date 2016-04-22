package io.clutchstud.nfems.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import io.clutchstud.nfems.R;
import io.clutchstud.nfems.models.Category;
import io.clutchstud.nfems.models.Protocol;
import io.clutchstud.nfems.retrofit.NFEMSService;
import io.clutchstud.nfems.retrofit.NFEMSServiceFactory;
import io.clutchstud.nfems.util.MenuPopulator;
import io.clutchstud.nfems.util.NavigationSubMenuPopulator;
import io.clutchstud.nfems.util.ProtocolTitleMenuItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Call<ArrayList<Category>> listCall;
    private Call<ArrayList<Protocol>> listProtocolCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

        NFEMSService retrofit = NFEMSServiceFactory.getNFEMSService();


        listCall = retrofit.listCategories();

        listProtocolCall = retrofit.listProtocols();



        Button queryButton = (Button) findViewById(R.id.queryButton);
        if (queryButton != null) {
            queryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listProtocolCall.clone().enqueue(new Callback<ArrayList<Protocol>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Protocol>> call, Response<ArrayList<Protocol>> response) {
                            Log.d("onResponse", "success");
                            for (Protocol category : response.body()) {
                                Log.d("category", category.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<Protocol>> call, Throwable t) {
                            t.printStackTrace();
                            Log.d("onFailure", t.getMessage());
                        }
                    });

                }
            });
        }


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
            Snackbar.make(this.findViewById(R.id.toolbar), "Feature not available", Snackbar.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Log.d("onNavigationItemSelected", "" + id);
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
}
