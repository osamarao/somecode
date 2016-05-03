package io.clutchstud.nfems.activities;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.Menu;
import android.view.SubMenu;

import java.util.Arrays;

import io.clutchstud.nfems.models.CategoryRealmObject;
import io.clutchstud.nfems.util.CategoryTitleMenuItem;
import io.clutchstud.nfems.util.MenuPopulator;
import io.clutchstud.nfems.util.NavigationSubMenuPopulator;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by a653h496 on 4/29/16.
 */
public class PopulateMenuFromDatabaseStrategy implements PopulateMenuStrategy {


    @Override
    public void populateMenu(Context ctx, NavigationView navigationView) {
        // TODO Clear Menu stuff here
        navigationView.getMenu().clear();



        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(ctx).build());
        RealmResults<CategoryRealmObject> categories = Realm.getDefaultInstance().where(CategoryRealmObject.class).findAll();
        Log.d("handleSomething", Arrays.deepToString(categories.toArray()));


        SubMenu categoryMenu = navigationView.getMenu().addSubMenu("Categories");

        MenuPopulator menuPopulator = new NavigationSubMenuPopulator(categoryMenu);
        for (CategoryRealmObject category : categories){
            menuPopulator.add(new CategoryTitleMenuItem(Menu.NONE, category.getId(), Menu.NONE, category.getCategory_name()));
        }
    }
}
