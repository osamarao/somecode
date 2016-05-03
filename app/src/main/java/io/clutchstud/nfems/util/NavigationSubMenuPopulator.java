package io.clutchstud.nfems.util;

import android.view.SubMenu;

import java.util.List;

/**
 * Created by a653h496 on 4/4/16.
 */
public class NavigationSubMenuPopulator implements MenuPopulator{

    private SubMenu subMenu;

    public NavigationSubMenuPopulator(SubMenu subMenu) {
        this.subMenu = subMenu;
    }
    @Override
    public void add(List<CategoryTitleMenuItem> categoryTitleMenuItems){
        for(CategoryTitleMenuItem categoryTitleMenuItem : categoryTitleMenuItems){
            subMenu.add(categoryTitleMenuItem.groupId, categoryTitleMenuItem.itemId, categoryTitleMenuItem.order, categoryTitleMenuItem.title);
        }
    }

    @Override
    public void add(CategoryTitleMenuItem categoryTitleMenuItem) {
        subMenu.add(categoryTitleMenuItem.groupId, categoryTitleMenuItem.itemId, categoryTitleMenuItem.order, categoryTitleMenuItem.title);
    }
}
