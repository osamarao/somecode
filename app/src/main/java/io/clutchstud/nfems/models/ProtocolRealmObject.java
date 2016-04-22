package io.clutchstud.nfems.models;

import io.realm.RealmObject;

/**
 * Created by a653h496 on 4/21/16.
 */
public class ProtocolRealmObject extends RealmObject {

    private final Integer id;
    private final String htmlcontent;
    private final String title;
    private final Integer categoryId;

    public ProtocolRealmObject(Integer id, String title, String htmlcontent, Integer categoryId) {
        this.id = id;
        this.title = title;
        this.htmlcontent = htmlcontent;
        this.categoryId = categoryId;
    }

    public ProtocolRealmObject(Protocol protocol) {
        this.id = protocol.getId();
        this.title = protocol.getTitle();
        this.htmlcontent = protocol.getHtmlcontent();
        this.categoryId = protocol.getCategoryId();
    }

    public Integer getId() {
        return id;
    }

    public String getHtmlcontent() {
        return htmlcontent;
    }

    public String getTitle() {
        return title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
}