package io.clutchstud.nfems.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by a653h496 on 4/21/16.
 */
public class ProtocolRealmObject extends RealmObject {
    @PrimaryKey
    private  Integer id;
    private  String htmlcontent;
    private  String title;
    private  Integer categoryId;

    public ProtocolRealmObject() {

    }

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

    @Override
    public String toString() {
        return "ProtocolRealmObject{" +
                "id=" + id +
                ", htmlcontent='" + htmlcontent + '\'' +
                ", title='" + title + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
