package io.clutchstud.nfems.models;

/**
 * Created by a653h496 on 4/14/16.
 */
public class Protocol {


    private Integer id;
    private String title;
    private String htmlcontent;
    private Integer category_id;
    private String createdAt;
    private String updatedAt;

    public Protocol() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtmlcontent() {
        return htmlcontent;
    }

    public void setHtmlcontent(String htmlcontent) {
        this.htmlcontent = htmlcontent;
    }

    public Integer getCategoryId() {
        return category_id;
    }

    public void setCategoryId(Integer categoryId) {
        this.category_id = categoryId;
    }

    @Override
    public String toString() {
        return "Protocol{" +
                "title='" + title + '\'' +
                ", htmlcontent='" + htmlcontent + '\'' +
                ", categoryId=" + category_id +
                '}';
    }


}
