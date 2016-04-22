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
    public void add(List<ProtocolTitleMenuItem> protocolTitleMenuItems){
        for(ProtocolTitleMenuItem protocolTitleMenuItem : protocolTitleMenuItems){
            subMenu.add(protocolTitleMenuItem.groupId, protocolTitleMenuItem.itemId, protocolTitleMenuItem.order, protocolTitleMenuItem.title);
        }
    }

    @Override
    public void add(ProtocolTitleMenuItem protocolTitleMenuItem) {
        subMenu.add(protocolTitleMenuItem.groupId, protocolTitleMenuItem.itemId, protocolTitleMenuItem.order, protocolTitleMenuItem.title);
    }
}
