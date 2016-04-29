package io.clutchstud.nfems.models;

import io.realm.RealmObject;

/**
 * Created by a653h496 on 4/21/16.
 */
public class CategoryRealmObject extends RealmObject {


    private  Integer id;
    private  String category_name;

    public CategoryRealmObject() {

    }

    public CategoryRealmObject(Category category) {
        this.id = category.getId();
        this.category_name =  category.getCategoryName();
    }

    public CategoryRealmObject(Integer id, String categoryName) {
        this.id = id;
        this.category_name = categoryName;

    }

    public Integer getId() {
        return id;
    }

    public String getCategory_name() {
        return category_name;
    }
}
