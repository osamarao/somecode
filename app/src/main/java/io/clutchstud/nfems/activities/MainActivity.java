package io.clutchstud.nfems.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.clutchstud.nfems.R;
import io.clutchstud.nfems.services.SyncProtocolsService;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PopulatesMenu {

    private RealmConfiguration realmConfiguration;
    private NavigationView navigationView;
    private PopulateMenuStrategy populateMenuStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // EventBus.getDefault().register(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        setPopulateMenuStrategy(new PopulateMenuFromDatabaseStrategy());
        populateMenuStrategy.populateMenu(this, navigationView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

    }

    @Subscribe
    public void handleSyncDoneEvent(SyncProtocolsService.SyncDoneEvent event){
        // We can now update the navigation
        Snackbar.make(this.findViewById(R.id.toolbar), "Sync Complete", Snackbar.LENGTH_SHORT).show();
//        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(this).build());
//        RealmResults<CategoryRealmObject> categories = Realm.getDefaultInstance().where(CategoryRealmObject.class).findAll();
//        Log.d("handleSomething", Arrays.deepToString(categories.toArray()));
//
//        Menu m = navigationView.getMenu();
//        SubMenu topChannelMenu = m.addSubMenu("Categories");
//
//        MenuPopulator menuPopulator = new NavigationSubMenuPopulator(topChannelMenu);
//        for (CategoryRealmObject category : categories){
//            menuPopulator.add(new CategoryTitleMenuItem(Menu.NONE, category.getId(), Menu.NONE, category.getCategory_name()));
//        }

        populateMenuStrategy.populateMenu(this, navigationView);
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        Intent contentActivityIntent = new Intent(this, ContentActivity.class);
        contentActivityIntent.putExtra("catId", id);

        startActivity(contentActivityIntent);

        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setPopulateMenuStrategy(PopulateMenuStrategy populateMenuStrategy) {

        this.populateMenuStrategy = populateMenuStrategy;
    }
}
