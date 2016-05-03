package io.clutchstud.nfems.util;

/**
 * Created by a653h496 on 4/4/16.
 */
public class CategoryTitleMenuItem {

     int groupId;
     int itemId;
     int order;
     CharSequence title;

    public CategoryTitleMenuItem(int groupId, int itemId, int order, CharSequence title){

        this.groupId = groupId;
        this.itemId = itemId;
        this.order = order;
        this.title = title;
    }
}
